import time

class Movement():
	def __init__ (self, brickpi3, log, senses, speed):
		# Speeds are negative due to robot design.
		self.speed = -speed

		self.update_interval = 0.01 # Seconds.

		self.BP = brickpi3
		self.log = log
		self.senses = senses

		self.left_motor = self.BP.PORT_C
		self.right_motor = self.BP.PORT_B

#direction-> 1=forward, -1=backward
	def move(self, direction):
		try:
			if(direction!=1 and direction!=-1):
				raise new_exc from Exception
		except Exception:
			self.log.warn("invalid direction")
			return False
		try:
			speed = self.speed * direction
			self.BP.set_motor_power(self.right_motor, speed)
			self.BP.set_motor_power(self.left_motor, speed)
			return -self.BP.get_motor_status(self.right_motor)[1]
		except KeyboardInterrupt:
			self.BP.reset_all()


	def moveforward(self):
		return self.move(1)


	def movebackward(self):
		self.log.info("Movement: Moving backwards.")
		return self.move(-1)


#direction-> 1=right, -1=left
	def turn(self, direction, duration):
		try:
			if(direction!=1 and direction!=-1):
				raise new_exc from Exception
		except Exception:
			self.log.warn("invalid direction")
			return 0
		try:
			speed = self.speed * direction

			self.BP.set_motor_power(self.right_motor, -speed)
			self.BP.set_motor_power(self.left_motor, speed)

			self.senses.update_sensors()
			time_rotated = 0
			while (time_rotated < duration and not self.senses.pressure_stop()):
				time.sleep(self.update_interval)
				time_rotated += self.update_interval
				self.senses.update_sensors()
			self.stop()
			return time_rotated
		except KeyboardInterrupt:
			self.BP.reset_all()

	def turndegrees(self, degrees):
		self.log.info("Trn: Movement: Turned: " + str(degrees))
		# At speed 30, turnspeed = 45 degrees/s (empiric).
		angular_speed = -self.speed * 1.5
		duration = degrees / angular_speed
		if(duration>0):
			return self.turn(1, duration) * angular_speed
		elif(duration<0):
			return -self.turn(-1, -duration) * angular_speed
		else:
			self.log.warn("0 degrees error")
			return 0

	def stop(self):
		self.BP.set_motor_power(self.right_motor, 0)
		self.BP.set_motor_power(self.left_motor, 0)
		self.log.info("Movement: Stop")
		return self.BP.get_motor_status(self.right_motor)[1]
