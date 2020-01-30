import time
from Abstract_senses import Abstract_senses

class Virtual_Senses(Abstract_senses):
	distances_list = []
	direction = 0
	dummy_colors = []
	dummy_left_pressed = []
	dummy_right_pressed = []

	def __init__(self, distances_list, dummy_colors, dummy_right_pressed, dummy_left_pressed):
		super(Virtual_Senses, self).__init__()
		self.dummy_distances_list = distances_list
		self.dummy_colors = dummy_colors
		self.dummy_right_pressed = dummy_right_pressed
		self.dummy_left_pressed = dummy_left_pressed

	def update_sensors(self):
		if(len(self.dummy_distances_list)>0):
			self.distance = dummy_distances_list.pop(0)
			if (len(self.distance_list) > 3):
				self.distance_list.pop(0)
			self.distance_list.append(self.distance)
		elif(len(self.distance_list)>0):
			self.distance = self.distance_list[-1]
		else:
			self.distance = 0

		if self.dummy_left_pressed:
			self.left_pressed = self.dummy_left_pressed.pop(0)
		else:
			self.left_pressed = False

		if self.dummy_right_pressed:
			self.right_pressed = self.dummy_right_pressed.pop(0)
		else:
			self.right_pressed = False

		if self.dummy_colors:
			new_color = self.dummy_colors.pop(0)
			if (new_color == self.avoid_color):
				self.last_seen_avoid_color = time.time()
			if not self.color_list:
				self.color_list.append(new_color)
			elif new_color != self.color_list[-1]:
					if len(self.color_list) > 2:
						self.color_list.pop(0)
					self.color_list.append(new_color)
		else:
			if not self.color_list:
				self.color_list.append("Blue")

	def reset(self):
		self.right_pressed = False
		self.left_pressed = False
		self.color_list = []
		self.distance_list = []
		self.dummy_colors = []
		self.dummy_right_pressed = []
		self.dummy_left_pressed = []


	def set_distance_list(self,distance_list):
		self.distance_list = distance_list

	def set_color_list(self,color_list):
		self.color_list = color_list

	def set_right_pressed(self,right_pressed):
		self.right_pressed = right_pressed

	def set_left_pressed(self,left_pressed):
		self.left_pressed = left_pressed
