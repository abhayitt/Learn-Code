import csv
import math


def read_data(file_name):
    locations = []
    with open(file_name, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            locations.append(row)
    return locations

def calculate_distance(i, j):
    x1,y1 = float(j[2]), float(j[3])
    x2,y2 = float(i['latitude']), float(i['longitude'])
    return math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)


def cluster_data(locations, radius):
    clusters = []
    initial_count= 0
    current_cluster = locations[0]
    clusters.append(["C"+str(initial_count),current_cluster['location'],current_cluster['latitude'],current_cluster['longitude']])
    for i in locations[1:]:
        flag = 0
        for j in clusters:
            distance = calculate_distance(i, j)
            if distance <= radius:
                j.append(i['location'])
                flag = 1
                break
        if not flag:
            initial_count =initial_count+1
            clusters.append(["C"+str(initial_count),i['location'],float(i['latitude']), float(i['longitude'])])
    return clusters


    
def main():
    file_name = "dataset.csv"
    radius = 10
    locations = read_data(file_name)
    clusters = cluster_data(locations, radius)
    print("Clusters formed are:",len(clusters))
    for cluster in clusters:
        # print(cluster[0],"contains",cluster[1:]
        print(cluster[0],"contains",cluster[1:2]+cluster[4:])
        
        

main()

