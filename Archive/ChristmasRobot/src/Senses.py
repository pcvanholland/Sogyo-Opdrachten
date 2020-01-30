import brickpi3
import time
from Movement import *
from Abstract_senses import Abstract_senses

class Senses(Abstract_senses):
	def __init__ (self, brickpi, movement):
		self.BP = brickpi
		self.movement = movement

		self.port_IR = self.BP.PORT_2
		self.port_color = self.BP.PORT_4
		self.port_pressure_r = self.BP.PORT_1
		self.port_pressure_l = self.BP.PORT_3

		self.BP.set_sensor_type(self.port_IR, self.BP.SENSOR_TYPE.EV3_INFRARED_PROXIMITY)
		self.BP.set_sensor_type(self.port_pressure_r, self.BP.SENSOR_TYPE.TOUCH)
		self.BP.set_sensor_type(self.port_pressure_l, self.BP.SENSOR_TYPE.TOUCH)
		self.BP.set_sensor_type(self.port_color, self.BP.SENSOR_TYPE.EV3_COLOR_COLOR)

		self.initialize_sensors()

	def set_movement(self, movement):
		self.movement = movement

	def initialize_sensors(self):
		initialized = False
		while not initialized:
			try:
				self.BP.get_sensor(self.port_IR)
				self.BP.get_sensor(self.port_pressure_r)
				self.BP.get_sensor(self.port_pressure_l)
				self.BP.get_sensor(self.port_color)
				initialized = True
			except brickpi3.SensorError as error:
				time.sleep(0.1)

	def update_sensors(self):

		try:
			self.distance = self.BP.get_sensor(self.port_IR)
			if not self.distance_list:
				self.distance_list.append(self.distance)
			elif self.distance != self.distance_list[-1]:
				if len(self.distance_list) > 3:
					self.distance_list.pop(0)
				self.distance_list.append(self.distance)
		except brickpi3.SensorError as error:
			print(str(error) + "IR sensor")

		try:
			value = self.BP.get_sensor(self.port_pressure_r)
			self.right_pressed = value > 0
		except brickpi3.SensorError as error:
			print(str(error) + "Right pressure sensor")

		try:
			value = self.BP.get_sensor(self.port_pressure_l)
			self.left_pressed = value > 0
		except brickpi3.SensorError as error:
			print(str(error) + "Left pressure sensor")

		try:
			index = self.BP.get_sensor(self.port_color)
			new_color = self.colors[index]
			if (new_color == self.avoid_color):
				self.last_seen_avoid_color = time.time()
			if not self.color_list:
				self.color_list.append(new_color)
			elif new_color != self.color_list[-1]:
				if len(self.color_list) > 2:
					self.color_list.pop(0)
				self.color_list.append(new_color)
		except brickpi3.SensorError as error:
			print(str(error) + "Color sensor")

	def turn_and_update_sensors(self, angle):
		self.movement.turndegrees(angle)
		self.update_sensors()
