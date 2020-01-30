import time

class Abstract_senses():
	colors = ["none", "Black", "Blue", "Green", "Yellow", "Red", "White", "Brown"]
	color_list = []
	distance = 0
	distance_list = []
	right_pressed = False
	left_pressed = False
	navigation_color = "Black"
	avoid_color = "Red"
	last_seen_avoid_color = 0


	def get_distance(self):
		return self.distance

	def get_pressed_right(self):
		return self.right_pressed

	def get_pressed_left(self):
		return self.left_pressed

	def get_color(self):
		if self.color_list:
			return self.color_list[-1]
		else:
			return "none"

	def is_navigation_color(self):
		return self.get_color() == self.navigation_color

	def is_avoid_color(self):
		return self.get_color() == self.avoid_color

	def pressure_stop(self):
		pressed_left = self.get_pressed_left()
		pressed_right = self.get_pressed_right()

		if pressed_left == 1 or pressed_right == 1:
			return True
		return False

	def color_stop(self):
		# When the list is small, we probably just started.
		if (len(self.color_list) < 2):
			return False
		color = self.get_color()

		# if self.color_list[-2] == self.navigation_color and self.color_list[-1] == self.avoid_color:
		# 	return False

		return color == self.avoid_color or color == self.navigation_color

	def distance_stop(self):
		delta_to_determine_gap = 6
		if(len(self.distance_list)>=3):
			comparison_first_and_second = self.distance_list[-1] - self.distance_list[-2] > delta_to_determine_gap
			comparison_first_and_third = self.distance_list[-1] - self.distance_list[-3] > 1.5*delta_to_determine_gap
			return comparison_first_and_second or comparison_first_and_third
		else:
			return False

	def finished(self):
		if self.color_list == [self.navigation_color, self.avoid_color, self.navigation_color]:
			return True
		return False

	def get_new_direction(self):
		self.turn_and_update_sensors(90)
		right_distance = self.get_distance()
		self.turn_and_update_sensors(-180)
		left_distance = self.get_distance()

		if left_distance > right_distance:
			return 0
		return 180

	def seen_avoid_color_recently(self):
		interval = 2 # seconds
		return time.time() - self.last_seen_avoid_color > interval

	def has_gap_on_right_side(self):
		distance_to_determine_gap = 20
		return self.get_distance()>distance_to_determine_gap

	def has_gap_on_right_side_while_in_red(self):
		distance_to_determine_gap = 22
		return self.get_distance()>distance_to_determine_gap
