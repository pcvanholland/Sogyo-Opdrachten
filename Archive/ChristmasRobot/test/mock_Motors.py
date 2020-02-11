class mock_Motors():
    def __init__ (self):
        self.PORT_B = 0
        self.PORT_C = 1
        self.motors = [0, 0]

    def set_motor_power(self, motor, speed):
        self.motors[motor] = speed

    def get_motor_status(self, motor):
        return ('null', self.motors[motor])

    def reset_all(self):
        self.motors[0] = 0
        self.motors[1] = 0
