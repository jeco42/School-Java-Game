"""
 Modified from source from:
 http://codeNtronix.com
 Developed by Leonel Machava <leonelmachava@gmail.com>
"""
import sys, math, pygame
from operator import itemgetter

class Point3D:
	def __init__(self, x = 0, y = 0, z = 0):
		#self.x, self.y, self.z = float(x), float(y), float(z)
		self.x, self.y, self.z = float(x)*0.9, float(y)*0.9, float(z)*0.9;

	def rotateX(self, angle):
		rad = angle * math.pi / 180
		cosa = math.cos(rad)
		sina = math.sin(rad)
		y = self.y * cosa - self.z * sina
		z = self.y * sina + self.z * cosa
		return Point3D(self.x, y, z)

	def rotateY(self, angle):
		rad = angle * math.pi / 180
		cosa = math.cos(rad)
		sina = math.sin(rad)
		z = self.z * cosa - self.x * sina
		x = self.z * sina + self.x * cosa
		return Point3D(x, self.y, z)

	def rotateZ(self, angle):
		rad = angle * math.pi / 180
		cosa = math.cos(rad)
		sina = math.sin(rad)
		x = self.x * cosa - self.y * sina
		y = self.x * sina + self.y * cosa
		return Point3D(x, y, self.z)

	def project(self, win_width, win_height, fov, viewer_distance):
		""" Transforms this 3D point to 2D using a perspective projection. """
		factor = fov / (viewer_distance + self.z)
		x = self.x * factor + win_width / 2
		y = -self.y * factor + win_height / 2
		return Point3D(x, y, self.z)

class Simulation:
	def __init__(self, win_width = 800, win_height = 800):
		pygame.init()

		self.screen = pygame.display.set_mode((win_width, win_height))
		pygame.display.set_caption("HyperChess Board Visualizer")

		self.clock = pygame.time.Clock()

		self.vertices = []

		# Define the vertices that compose each of the 6 faces. These numbers are indices to the vertices list defined above.
		self.faces  = []
		self.colors = []

		self.width = []
		self.piece = [];

		self.angle = 0

	def createNode(self, x, y, z, piece):
		width, red, green, blue = 2, 0, 127, 0;
		red2, green2, blue2 = 0, 127, 0;
		if(piece == "WPawn"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;
		if(piece == "WRook"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;
		if(piece == "WKnight"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;
		if(piece == "WBishop"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;
		if(piece == "WQueen"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;
		if(piece == "WKing"):
			width, red, green, blue = 4, 255, 255, 255;
			red2, green2, blue2 = 255, 255, 255;

		if(piece == "BPawn"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;
		if(piece == "BRook"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;
		if(piece == "BKnight"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;
		if(piece == "BBishop"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;
		if(piece == "BQueen"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;
		if(piece == "BKing"):
			width, red, green, blue = 4, 0, 0, 255;
			red2, green2, blue2 = 0, 0, 0;

		offset = len(self.vertices);
		self.vertices.append(Point3D(x+0,y+0,z+0));
		self.vertices.append(Point3D(x+0,y+1,z+0));
		self.vertices.append(Point3D(x+1,y+1,z+0));
		self.vertices.append(Point3D(x+1,y+0,z+0));
		self.vertices.append(Point3D(x+0,y+0,z+1));
		self.vertices.append(Point3D(x+0,y+1,z+1));
		self.vertices.append(Point3D(x+1,y+1,z+1));
		self.vertices.append(Point3D(x+1,y+0,z+1));

		#self.vertices.append(Point3D(x+0.3,y+0.3,z+0.5));
		#self.vertices.append(Point3D(x+0.7,y+0.3,z+0.5));
		#self.vertices.append(Point3D(x+0.7,y+0.7,z+0.5));
		#self.vertices.append(Point3D(x+0.3,y+0.7,z+0.5));

		self.vertices.append(Point3D(x+0.2,y+0.2,z+0.5));
		self.vertices.append(Point3D(x+0.8,y+0.2,z+0.5));
		self.vertices.append(Point3D(x+0.8,y+0.8,z+0.5));
		self.vertices.append(Point3D(x+0.2,y+0.8,z+0.5));

		#self.faces.append((0+offset,1+offset,2+offset,3+offset));
		#self.faces.append((1+offset,5+offset,6+offset,2+offset));
		#self.faces.append((5+offset,4+offset,7+offset,6+offset));
		#self.faces.append((4+offset,0+offset,3+offset,7+offset));
		#self.faces.append((0+offset,4+offset,4+offset,1+offset));
		#self.faces.append((3+offset,2+offset,6+offset,7+offset));

		self.faces.append((0+offset,1+offset,2+offset,3+offset));
		self.faces.append((4+offset,5+offset,6+offset,7+offset));
		self.faces.append((2+offset,6+offset,7+offset,3+offset));
		self.faces.append((1+offset,5+offset,4+offset,0+offset));
		self.faces.append((0+offset,4+offset,7+offset,3+offset));
		self.faces.append((1+offset,5+offset,6+offset,2+offset));

		#self.faces.append((1+offset,7+offset,1+offset,7+offset));
		#self.faces.append((5+offset,3+offset,5+offset,3+offset));
		#self.faces.append((6+offset,0+offset,6+offset,0+offset));
		#self.faces.append((2+offset,4+offset,2+offset,4+offset));

		self.faces.append((8+offset,9+offset,10+offset,11+offset));

		self.colors.append((red,green,blue));
		self.colors.append((red,green,blue));
		self.colors.append((red,green,blue));
		self.colors.append((red,green,blue));
		self.colors.append((red,green,blue));
		self.colors.append((red,green,blue));
		self.width.append(width/2);
		self.width.append(width/2);
		self.width.append(width/2);
		self.width.append(width/2);
		self.width.append(width/2);
		self.width.append(width/2);


		#self.colors.append((red,green,blue));
		#self.colors.append((red,green,blue));
		#self.colors.append((red2,green2,blue2));
		#self.colors.append((red2,green2,blue2));
		#self.width.append(width);
		#self.width.append(width);
		#self.width.append(width);
		#self.width.append(width);

		self.colors.append((red2,green2,blue2));
		self.width.append(width);

		self.piece.append("none");
		self.piece.append("none");
		self.piece.append("none");
		self.piece.append("none");
		self.piece.append("none");
		self.piece.append("none");
		self.piece.append(piece);


	def run(self):
		datafile = open('currentstate', 'r');
		dataset = datafile.readlines();
		for line in dataset:
			items = line.split();
			if(len(items) == 0):
				break;
			print items

			self.createNode(int(items[0]), int(items[1]), int(items[2]), items[3]);

		while 1:
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					pygame.quit()
					sys.exit()

			self.clock.tick(30)
			self.screen.fill((0,0,0))

			# It will hold transformed vertices.
			t = []

			for v in self.vertices:
				r = v.rotateX(self.angle).rotateY(self.angle).rotateZ(self.angle)
				# Transform the point from 3D to 2D
				p = r.project(self.screen.get_width(), self.screen.get_height(), 512, 8)
				t.append(p)

			# Calculate the average Z values of each face.
			avg_z = []
			i = 0
			for f in self.faces:
				z = (t[f[0]].z + t[f[1]].z + t[f[2]].z + t[f[3]].z) / 4.0
				avg_z.append([i,z])
				i = i + 1

			# Draw the faces using the Painter's algorithm: Distant faces are drawn before the closer ones.
			for tmp in sorted(avg_z,key=itemgetter(1),reverse=True):
				face_index = tmp[0]
				f = self.faces[face_index]
				pointlist = [(t[f[0]].x, t[f[0]].y), (t[f[1]].x, t[f[1]].y),
							 (t[f[1]].x, t[f[1]].y), (t[f[2]].x, t[f[2]].y),
							 (t[f[2]].x, t[f[2]].y), (t[f[3]].x, t[f[3]].y),
							 (t[f[3]].x, t[f[3]].y), (t[f[0]].x, t[f[0]].y)]
				if(self.piece[face_index] == "none"):
					rect = pygame.draw.polygon(self.screen,self.colors[face_index],pointlist,self.width[face_index])
					
				elif(self.piece[face_index] != "null"):
					surface = pygame.image.load(self.piece[face_index] + ".png");
					rect = pygame.draw.polygon(self.screen,(0,0,0,255),pointlist,1)
					surface = pygame.Surface.convert_alpha(surface);
					surface = pygame.transform.scale(surface, (rect.height, rect.width));
					surface = pygame.transform.rotate(surface, self.angle);
					self.screen.blit(surface, rect);



			self.angle += 1

			pygame.display.flip()

if __name__ == "__main__":
	Simulation().run()
