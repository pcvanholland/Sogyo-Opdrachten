import Senses
import brickpi3
import Movement
import Log

bp = brickpi3.BrickPi3()
l = Log.Log()
s = Senses.Senses(bp, None)
m = Movement.Movement(bp, l, s, 30)
s.set_movement(m)
# angle = s.get_furtherst_distance_angle(m)
# print(angle)
front_angle = 50
# while abs(front_angle) > 5:
tmp = s.get_front_right_angle()

front_angle = tmp
m.turndegrees(front_angle)
print(tmp)
    
    

s.update_sensors()
print(s.get_color())
