from pylab import *
from mpl_toolkits.mplot3d import Axes3D
from matplotlib.cbook import get_sample_data
from matplotlib._png import read_png
import sys


z1=float(sys.argv[1])
z2=float(sys.argv[2])

fn = get_sample_data("c1_result.png", asfileobj=False)
img = read_png(fn)
x, y = ogrid[0:img.shape[0], 0:img.shape[1]]
ax = gca(projection='3d')
ax.plot_surface(x, y, z1, rstride=5, cstride=5, facecolors=img)
ax.plot_surface(x, y, z2, rstride=5, cstride=5, facecolors=img)
show()

