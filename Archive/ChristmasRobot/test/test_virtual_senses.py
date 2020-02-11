import sys
import os
sys.path.append(os.path.abspath('../src'))
import Virtual_Senses
import unittest

class Test_Virtual_Senses(unittest.TestCase):

    def test_get_color(self):
        colors = ["Red", "Blue", "White"]
        vs = Virtual_Senses.Virtual_Senses([], colors, [], [])
        vs.update_sensors()
        self.assertEqual(vs.get_color(), "Red")
        vs.update_sensors()
        self.assertEqual(vs.get_color(), "Blue")
        vs.update_sensors()
        self.assertEqual(vs.get_color(), "White")

    def test_get_pressed_left(self):
        press_left_list = [True, False, True]
        vs = Virtual_Senses.Virtual_Senses([], [], [], press_left_list)
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_left(), True)
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_left(), False)
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_left(), True)

    def test_get_pressed_right(self):
        press_right_list = [True, False, True]
        vs = Virtual_Senses.Virtual_Senses([], [], press_right_list, [])
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_right(), True)
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_right(), False)
        vs.update_sensors()
        self.assertEqual(vs.get_pressed_right(), True)

    def test_distance_stop_by_comparison_with_second(self):
        vs = Virtual_Senses.Virtual_Senses([], [], [], [])
        vs.distance_list = [0, 0, 10]
        self.assertEqual(vs.distance_stop(), True)

    def test_distance_stop_by_comparison_with_third(self):
        vs = Virtual_Senses.Virtual_Senses([], [], [], [])
        vs.distance_list = [0, 5, 11]
        self.assertEqual(vs.distance_stop(), True)

    def test_no_distance_stop_when_detecting_obstacle(self):
        vs = Virtual_Senses.Virtual_Senses([], [], [], [])
        vs.distance_list = [11, 5, 0]
        self.assertEqual(vs.distance_stop(), False)

if __name__ == '__main__':
	unittest.main()
