import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.*;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class ImageMod
{

	BufferedImage image1;
	
	/*BufferedImage imagefin;*/
	
	File input;
	
	int w;
	int h;
	int pixels[][];
	
	
	//Builds a connection matrix out of the pixels.
	int conn[][];
	
	
	// Code in void did not execute..
		
	ImageMod()throws IOException
	{
	
		//input=new File("c.png");
		input=new File("imgblack.png");
		
		image1=ImageIO.read(input);	
		
		/*imagefin=ImageIO.read(input);*/
		
		h=image1.getHeight();
		w=image1.getWidth();


		System.out.print(h + " " + w);
		System.out.println();

		/*
		if(input.exists())
		{
			System.out.println("The mercury file exists");
		}
		else
		{
			System.out.println("The mercury file does not exist");
		}
		*/
		
	}

	ImageMod(String s)throws IOException
	{
		input=new File(s);
		image1=ImageIO.read(input);	
		h=image1.getHeight();
		w=image1.getWidth();	
	}
	
	void regions()throws IOException
	{
		/*
		for(int i=0;i<w;i++)
		{
			for(int j=0;j<h;j++)
			{				
			}
		}
		*/			
		//System.out.println("It is not a null thankfully");	
		
		
		pixels=new int[h][w];
		conn = new int[h][w];
		
		FastRGB frgb=new FastRGB(image1);
		int i=1;
		int j=1;
		
	
		
		try
		{
			for(i=0;i<h;i++)
			{
				for(j=0;j<w;j++)
				{
					
					pixels[i][j]=frgb.getRGB(j,i);
					//System.out.println(pixels[i][j]);
					if(pixels[i][j]==-1)
					{
						conn[i][j]=1;
					}
					else
					{
						conn[i][j]=2;
					}
					// By this stage we have a connectivity matrix that show the connected components.
				}
			}
		
			// morphological dilation
			for(i=0; i<h; i++)
			{
				for(j=0; j<w; j++)
				{
					if(conn[i][j]==2)
					{
						conn[i-1][j-1]=0;
						conn[i-1][j]=0;
						conn[i-1][j+1]=0;
						conn[i][j-1]=0;
						conn[i][j]=0;
						conn[i][j+1]=0;
						conn[i+1][j-1]=0;
						conn[i+1][j]=0;
						conn[i+1][j+1]=0;
					}
				}
			}

			
		
		/*	for(i=0; i<h; i++)
			{
				for(j=0; j<w; j++)	
				{
					if(conn[i][j]==0)
					{
						/*if(conn[i-1][j]==1||conn[i-1][j-1]==1||conn[i-1][j+1]==1||conn[i][j-1]==1||conn[i][j+1]==1||conn[i+1][j-1]==1||conn[i+1][j]==1||conn[i+1][j+1]==1)
						{
							conn[i][j]=2;
						}*/

									
		/*				if(conn[i-1][j]==1||conn[i][j-1]==1||conn[i][j+1]==1||conn[i+1][j]==1)
						{
							conn[i][j]=2;
						}	
					}
				}	
			}		
						
			for(i=0; i<h; i++)
			{
				for(j=0; j<w; j++)
				{
					if(conn[i][j]==2)
						conn[i][j]=1;
				}
			}

		*/	

			//text file output

		/*	for(i=0;i<h;i++)
			{
				for(j=0;j<w;j++)
				{
					
					
					System.out.print(conn[i][j]);
					
				}
				System.out.println();
			}

		*/
			//image reconstruction
		
				for(i=0; i<h; i++)
				{
					for(j=0; j<w; j++)
					{
						if(conn[i][j]==0)
						{
							Color newColor = new Color(143,143,143);
							image1.setRGB(j,i,newColor.getRGB());
						}
					}
				}					
			

			
			
			
			File outputfile = new File("cc1_result.png");
			ImageIO.write(image1, "png", outputfile);
			

		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Some issue with your thingy ");
		}
			
		//System.out.println(frgb.getRGB(4,4));
	}
	
	void floodfill() throws IOException
	{
	
		
		
		int col=2;
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				if(conn[i][j]==1)
				{
					//xml.append(New region - col)					
					flood(i,j,col++);
					
				}
			}
		}
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{				
				//System.out.print(imagefin.getRGB(j,i));
				//System.out.print(conn[i][j]);
					
				if(conn[i][j]<0)	
				{
					Color newColor = new Color(143,143,143);
					image1.setRGB(j,i,newColor.getRGB());
				}
				else if(conn[i][j]==0)
				{
					Color newColor = new Color(255,255,255);
					image1.setRGB(j,i,newColor.getRGB());
				}
				else if(conn[i][j]>0)
				{
					int a,b,c;
					a=(conn[i][j]*2)%255;
					b=(conn[i][j]*3 +60)%255;
					c=(conn[i][j]+90)%255;
					Color newColor = new Color(a,b,c);
					image1.setRGB(j,i,newColor.getRGB());
				}			
			}
			//System.out.println();
		}

		File outputfile = new File("cc2_result.png");
		ImageIO.write(image1, "png", outputfile);
	
		//System.out.print(col);

		System.out.println("Enter the height of the arbitrary region");
	
	        Scanner in=new Scanner(System.in);
		int height=in.nextInt();

		System.out.println("Enter the point to be checked");
	
		int x1=in.nextInt();
		int y1=in.nextInt();
		int z1=in.nextInt();

		//System.out.println(x + " "+ y+" "+z);

		if(z1<=height && conn[x1][y1]!=2)
			System.out.println("It lies inside the arbitrary region");
		else
			System.out.println("It lies outside the arbitrary region");
			
	}
	
	void flood(int x,int y,int no_of_colour) throws IOException
	{		
	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//String s=br.readLine();
	
		conn[x][y]=no_of_colour;
		
  		Deque<Integer> xq = new LinkedList<Integer>();
  		Deque<Integer> yq = new LinkedList<Integer>();
  		
  		xq.addFirst(x);
  		yq.addFirst(y);
  		
		while (  xq.peekFirst()!=null )
		{
		
			//s=br.readLine();
		
			//System.out.println(xq.size());
		
      			int x1 = xq.removeLast();
       			int y1 = yq.removeLast();
		
			if(x1+1<h)
			{
       				if(conn[x1+1][y1]==1)
            			{
            				//System.out.println((x1+1)+" "+y1);
            				xq.addFirst(x1+1);
            				yq.addFirst(y1);
            				conn[x1+1][y1]=no_of_colour;
            				//System.out.println("Here1");
            			}
				if(conn[x1+1][y1]==0)
				{
					conn[x1+1][y1]=-(no_of_colour);
					// xml.append(x+1,y1)
				}
            		}
            	
			if(x1-1>=0)
			{            	
       				if(conn[x1-1][y1]==1)
            			{
            				//System.out.println((x1-1)+" "+y1);
            				xq.addFirst(x1-1);
            				yq.addFirst(y1);
            				conn[x1-1][y1]=no_of_colour;
            				//System.out.println("Here2");
            			}
				if(conn[x1-1][y1]==0)
				{
					conn[x1-1][y1]=-(no_of_colour);
				}
            		}
            		
            		if(y1+1<w)
            		{
            			
       				if(conn[x1][y1+1]==1)
            			{
            				//System.out.println(x1+" "+(y1+1));
            				xq.addFirst(x1);
            				yq.addFirst(y1+1);
            				conn[x1][y1+1]=no_of_colour;
            				//System.out.println("Here3");
            			}
				if(conn[x1][y1+1]==0)
				{
					conn[x1][y1+1]=-(no_of_colour);
				}
            		}
            		
            		if(y1-1>=0)
            		{
       				if (conn[x1][y1-1]==1)
            			{
            				//System.out.println(x1+" "+(y1-1));
            				xq.addFirst(x1);
            				yq.addFirst(y1-1);
            				conn[x1][y1-1]=no_of_colour;
            				//System.out.println("Here4");
            			}
				if(conn[x1][y1-1]==0)
				{
					conn[x1][y1-1]=-(no_of_colour);
				}
			}
		}		
	}
	

	public static void main(String [] args) throws IOException
	{	
		
		ImageMod m=new ImageMod();		
		m.regions();		
		m.floodfill();	
		
	}
	
}
	
	
	
	
