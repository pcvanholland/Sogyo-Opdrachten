import os
import sys
sys.path.append(os.path.abspath('../src'))

import unittest
import mock_Log
import mock_Movement
import Navigation
import Virtual_Senses

class Test_Navigation(unittest.TestCase):
    def setUp(self):
        self.navigation_color = "Black"
        self.stop_color = "Red"
        self.other_color = "Blue"
        self.mock_senses = Virtual_Senses.Virtual_Senses([],[],[],[])
        self.mock_movement = mock_Movement.mock_Movement()
        self.navigation = Navigation.Navigation(mock_Log.mock_Log(), 0, self.mock_movement, self.mock_senses)

    def test_may_move_forward(self):

        self.assertTrue(self.navigation.may_move_forward())
        self.mock_senses.right_pressed = True
        self.assertFalse(self.navigation.may_move_forward())
        self.mock_senses.reset()
        self.mock_senses.color_list = [self.other_color,self.stop_color]
        self.assertFalse(self.navigation.may_move_forward())
        self.mock_senses.reset()
        self.mock_senses.distance_list = [0,0,20]
        self.assertFalse(self.navigation.may_move_forward())
        # No reset to verify that two different reasons still stops us.
        self.mock_senses.color_list = [self.other_color,self.stop_color]
        self.assertFalse(self.navigation.may_move_forward())
        self.mock_senses.reset()
        self.assertTrue(self.navigation.may_move_forward())
        self.mock_senses.reset()


    def test_handle_finish(self):
        self.mock_senses.color_list =[self.other_color,self.navigation_color,self.stop_color]
        self.mock_senses.dummy_colors = [self.navigation_color]
        self.mock_senses.dummy_right_pressed = [False,False,False,False,True,True,True,False,False,False]
        self.assertTrue(self.navigation.handle_finish())
        self.mock_senses.reset()

    def test_try_move_forward(self):
        self.mock_senses.color_list =[self.other_color,self.other_color,self.other_color]
        self.mock_senses.dummy_colors = [self.navigation_color,self.navigation_color,self.navigation_color,self.stop_color,self.other_color,self.other_color,self.navigation_color]
        self.assertTrue(self.navigation.try_move_forward())
        self.mock_senses.reset()

    def test_try_to_turn(self):
        degrees = 20
        self.assertTrue(self.navigation.try_to_turn(degrees))
        self.mock_senses.reset()

    def test_move_from_obstacle(self):
        self.mock_senses.dummy_right_pressed = [True,True,True,True]
        self.assertTrue(self.navigation.move_from_obstacle())
        self.mock_senses.reset()

    def test_time_to_move_over_navigation_colour(self):
        self.assertAlmostEqual(self.navigation.time_to_move_over_navigation_colour(), self.navigation.update_interval/10, 1)
        # Aye, we got out of Black, but we encountered a stop-colour.
        self.mock_senses.color_list = [self.navigation_color,self.navigation_color,self.navigation_color,self.stop_color]
        self.assertFalse(self.navigation.time_to_move_over_navigation_colour())
        self.mock_senses.reset()

    def test_move_out_of_colour(self):
        self.assertTrue(self.navigation.move_out_of_colour(self.navigation_color))
        # Aye, we got out of Black, but we encountered a stop-colour.
        self.mock_senses.color_list = [self.navigation_color,self.navigation_color,self.navigation_color,self.stop_color]
        self.assertFalse(self.navigation.move_out_of_colour(self.navigation_color))
        self.mock_senses.reset()

if __name__ == '__main__':
	unittest.main()
