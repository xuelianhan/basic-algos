import numpy as np
import matplotlib.pyplot as plt
import importlib
importlib.import_module('mpl_toolkits').__path__
import mpl_toolkits
from mpl_toolkits.mplot3d import Axes3D
fig = plt.figure(figsize=(8,8))
ax = fig.gca(projection='3d')
[x, t] = np.meshgrid(np.array(range(25)) / 24.0, np.arange(0, 575.5, 0.5) / 575 * 30 * np.pi - 4*np.pi)
p = (np.pi / 2) * np.exp(-t / (8 * np.pi))
change = np.sin(20*t)/50
u = 1 - (1 - np.mod(3.3 * t, 2 * np.pi) / np.pi) ** 4 / 2 + change
y = 2 * (x ** 2 - x) ** 2 * np.sin(p)
r = u * (x * np.sin(p) + y * np.cos(p)) * 1.5
h = u * (x * np.cos(p) - y * np.sin(p))
c= plt.get_cmap('magma')
surf = ax.plot_surface(r * np.cos(t), r * np.sin(t), h, rstride=1, cstride=1,
                       cmap= c, linewidth=0, antialiased=True)

ax.set_xticks([])
ax.set_yticks([])
ax.set_zticks([])

#from matplotlib.font_manager import FontProperties
#font_set = FontProperties(fname=r"C:\Windows\Fonts\STFANGSO.TTF",size=20)
plt.title('love you so much', fontproperties=font_set)
plt.show()

