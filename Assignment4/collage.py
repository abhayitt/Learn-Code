import os
import requests
from PIL import Image
from flask import Flask, render_template, send_from_directory

def fetch_pexels_images(api_key, count=12):
    pexels_api_url = 'https://api.pexels.com/v1/curated'
    headers = {'Authorization': api_key}
    params = {'per_page': count}

    try:
        response = requests.get(pexels_api_url, headers=headers, params=params)
        response.raise_for_status()
        data = response.json()
        image_urls = [photo['src']['original'] for photo in data['photos']]
        return image_urls
    except requests.exceptions.RequestException as e:
        print(f"Error fetching images from Pexels: {e}")
        return None

def download_images(image_urls, save_directory='images'):
    os.makedirs(save_directory, exist_ok=True)

    for index, image_url in enumerate(image_urls, start=1):
        try:
            response = requests.get(image_url)
            response.raise_for_status()
            save_path = os.path.join(save_directory, f'image_{index}.jpg')
            save_image(response.content, save_path)
        except requests.exceptions.RequestException as e:
            print(f"Error downloading image from {image_url}: {e}")

def save_image(content, save_path):
    with open(save_path, 'wb') as file:
        file.write(content)

def create_collage(image_paths, collage_path, target_size, max_columns):
    os.makedirs(os.path.dirname(collage_path), exist_ok=True)

    num_images = len(image_paths)
    rows, columns = calculate_rows_columns(num_images, max_columns)

    collage_width = target_size[0] * columns
    collage_height = target_size[1] * rows

    collage = Image.new('RGB', (collage_width, collage_height))

    for i, image_path in enumerate(image_paths):
        try:
            paste_image_on_collage(collage, image_path, target_size, i, columns)
        except Exception as e:
            print(f"Error processing image {i+1}: {e}")

    save_collage(collage, collage_path)

def calculate_rows_columns(num_images, max_columns=3):
    columns = min(max_columns, num_images)
    rows = (num_images + columns - 1) // columns
    return rows, columns

def paste_image_on_collage(collage, image_path, target_size, index, columns):
    img = Image.open(image_path)
    img = img.resize(target_size)
    row = index // columns
    col = index % columns
    collage.paste(img, (col * target_size[0], row * target_size[1]))

def save_collage(collage, collage_path):
    try:
        collage.save(collage_path)
    except Exception as e:
        print(f"Error creating collage: {e}")

def run_flask_app():
    app = Flask(__name__)

    @app.route('/')
    def index():
        return render_template('index.html')

    @app.route('/static/<path:filename>')
    def serve_static(filename):
        return send_from_directory('static', filename)

    if __name__ == '__main__':
        app.run(debug=True)

def main(api_key):
    image_urls = fetch_pexels_images(api_key)
    
    if image_urls:
        download_images(image_urls)
        image_paths = [f'images/image_{i + 1}.jpg' for i in range(len(image_urls))]
        target_size = (200, 200)
        max_columns = 4
        create_collage(image_paths, 'static/collage.jpg', target_size, max_columns)
        run_flask_app()

if __name__ == '__main__':
    pexels_api_key = 'lWAJyMGT9khy2ApTBoDc6wSkIDYYRD9Qyj4wGYxFFHJbqG8KC5WxgBQ5' 
    main(pexels_api_key)
