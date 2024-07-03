import matplotlib.pyplot as plt
import numpy as np
colors = ['b','g','r','c','m','y','k']
global rectSum

def plotTrajectories(trajectoryFile):
    delimiter = ","
    fd = open(trajectoryFile)
    lines = fd.readlines()
    #users = int(lines[0])
    users = 7
    counter = 1
    color = 0
    for i in range(users):
        pointsX = np.array([])
        pointsY = np.array([])
        while(int(lines[counter].split(delimiter)[0])==i):
            arr = lines[counter].split(delimiter)
            pointsX= np.append(pointsX,float(arr[2]))
            pointsY = np.append(pointsY,float(arr[3]))
            counter+=1
            if(counter>=len(lines)):
                break
        plt.plot(pointsX,pointsY,colors[color])
        color=(color+1)%len(colors)
    #plt.xlim(0,2000)
    #plt.ylim(0,2000)
    #plt.savefig(trajectoryFile+".png")
    plt.show()

def plotRectangles(rectFile):
    delimiter = " "
    rectSum = 0
    fd = open(rectFile)
    lines = fd.readlines()
    #users = int(lines[0])
    users = 1000
    counter = 1
    color = 0
    for i in range(users):
        previous = int(lines[counter].split(delimiter)[0])
        rectNum = 0
        while(previous == int(lines[counter].split(delimiter)[0])):
            previous = int(lines[counter].split(delimiter)[0])
            pointsX = np.array([])
            pointsY = np.array([])
            arr = lines[counter].split(delimiter)
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
            rectNum+=1
            #plt.plot(pointsX,pointsY,colors[color])
            if(counter>=len(lines)):
                break
        color=(color+1)%len(colors)
        print("User ",i," Rectangles: ",rectNum)
        rectSum += rectNum
    print("Average: " ,rectSum/users)
    #plt.xlim(0,2000)

    #plt.savefig(rectFile+".png",transparent=True)
    #plt.show()



#plotTrajectories("/home/lookos/CS/Thesis/Generated/users.csv")
#plotTrajectories("/media/lookos/LUKUSB/ShoppingCenter/UserTrajectories.csv")
plotTrajectories("/home/lookos/Dropbox/CustomTrajectoryGenerator/printable.csv")

