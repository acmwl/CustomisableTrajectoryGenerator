import matplotlib.pyplot as plt
import numpy as np
colors = ['b','g','r','c','m','y','k','w']


def plotTrajectories(trajectoryFile):
    fd = open(trajectoryFile)
    lines = fd.readlines()
    users = int(lines[0])
    counter = 1
    color = 0
    for i in range(users):
        pointsX = np.array([])
        pointsY = np.array([])
        while(int(lines[counter].split(",")[0])==i):
            arr = lines[counter].split(",")
            pointsX= np.append(pointsX,float(arr[2]))
            pointsY = np.append(pointsY,float(arr[3]))
            counter+=1
            if(counter>=len(lines)):
                break
        plt.plot(pointsX,pointsY,colors[color])
        color=(color+1)%len(colors)
    plt.show()

def plotRectangles(rectFile):
    fd = open(rectFile)
    lines = fd.readlines()
    users = int(lines[0])
    counter = 1
    color = 0
    for i in range(users):
        while(int(lines[counter].split(" ")[0])==i):
            pointsX = np.array([])
            pointsY = np.array([])
            arr = lines[counter].split(" ")
            #start
            pointsX = np.append(pointsX,float(arr[5]))#xstart 0 1 2 3 4
            pointsY = np.append(pointsY,float(arr[6]))#ystart
            #up
            pointsX= np.append(pointsX,float(arr[5]))#xstart
            pointsY = np.append(pointsY,float(arr[8]))#yend
            #right
            pointsX= np.append(pointsX,float(arr[7]))#xend
            pointsY = np.append(pointsY,float(arr[8]))#yend
            #down
            pointsX= np.append(pointsX,float(arr[7]))#xend
            pointsY = np.append(pointsY,float(arr[6]))#ystart
            #left
            pointsX= np.append(pointsX,float(arr[5]))#xstart
            pointsY = np.append(pointsY,float(arr[6]))#ystart
            counter+=1
            if(counter>=len(lines)):
                break
            plt.plot(pointsX,pointsY,colors[color])
        color=(color+1)%len(colors)
    plt.show()
    


