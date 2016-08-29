# Python---Neuroconstruct

Visualising 3D regions with arbitrary cross section, on neuroconstruct.

We have added a new functionality in neuroconstruct that helps in visualising 3D regions (neurons) through arbitrary cross sectional images.

Requirements to run this demo:

Neuroconstruct + Python (Matplotlib, numpy, scipy)

Files containing GUI changes/addition : 

 - neuroConstruct/src/ucl/physiol/neuroconstruct/project/RegionTypeHelper.java

 - neuroConstruct/src/ucl/physiol/neuroconstruct/project/ArbitraryRegion.java

 - RegionTypeHelper.java provides utility to create a new instance of arbitraryregion whose parameters are taken from ArbitraryRegion.java


Files containing visualisation code : lines.py, better_plot.py
This is integrated with neuroconstruct's frontend API through  /neuroConstruct/src/ucl/physiol/neuroconstruct/gui/MainFrame.java 


Feed the raw images into image_processing module, this does the closing of edges and region based segmentation.Place all these processed images in the folder matplotlib/mpl-data/sample_data, then run neuroconstruct.
