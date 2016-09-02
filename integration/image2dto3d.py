from pylab import *
from mpl_toolkits.mplot3d import Axes3D
from matplotlib.cbook import get_sample_data
from matplotlib._png import read_png
import sys

from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
from matplotlib.ticker import LinearLocator
import matplotlib.pyplot as plt
import numpy as np

from matplotlib import pyplot
import pylab
from mpl_toolkits.mplot3d import Axes3D
import random


z1=float(sys.argv[1])
z2=float(sys.argv[2])

fn = get_sample_data("c2_result.png", asfileobj=False)
img = read_png(fn)

ax = gca(projection='3d')

sequence_containing_x_vals = range(200,400)	#These are adjustable
sequence_containing_y_vals = range(200,400)
sequence_containing_z_vals = range(100,300)

random.shuffle(sequence_containing_x_vals)
random.shuffle(sequence_containing_y_vals)
random.shuffle(sequence_containing_z_vals)
ax.scatter(sequence_containing_x_vals, sequence_containing_y_vals, sequence_containing_z_vals)


#colors = ColorConverter.to_rgba('#FF0000',0.5)
#print img.shape
for x in range(img.shape[0]):
    for y in range(img.shape[1]):
        if img[x][y][0]+img[x][y][1]+img[x][y][2] == 3:
            lines=ax.plot([x,x],[y,y],[0,z2])
            
    
x, y = ogrid[0:img.shape[0], 0:img.shape[1]]
#print x,y
#print len(img)

#ax.plot_surface(x, y, z1, rstride=5, cstride=5, facecolors=img)

#ax.plot_surface(x, y, 1000, rstride=5, cstride=5, facecolors=img)
#ax.plot_surface(x, y, 10, rstride=5, cstride=5, facecolors=img)




show()
