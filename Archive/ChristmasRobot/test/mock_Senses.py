class mock_Senses():
	def __init__ (self):
		self.reset()

	def reset(self):
		self.pressure_stopped = False
		self.colour_stopped = False
		self.distance_stopped = False
		self.current_colour = "None"
		self.furthest_distance_angle = 10
		self.pressed_left = False
		self.pressed_right = False
		self.navigation_color = "Black"
		self.avoid_color = "Red"
		self.is_finished = False

	def update_sensors(self):
		return

	def pressure_stop(self):
		return self.pressure_stopped

	def distance_stop(self):
		return self.distance_stopped

	def color_stop(self):
		return self.colour_stopped

	def get_color(self):
		return self.current_colour

	def get_pressed_left(self):
		return self.pressed_left

	def get_pressed_right(self):
		return self.pressed_right

	def get_new_direction(self):
		return self.furthest_distance_angle

	def is_avoid_color(self):
		return self.current_colour == self.avoid_color

	def is_navigation_color(self):
		return self.current_colour == self.navigation_color

	def finished(self):
		return self.is_finished
