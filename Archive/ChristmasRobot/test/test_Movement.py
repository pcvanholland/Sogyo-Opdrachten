import unittest
import mock_Log
import mock_Motors
import mock_Senses

import os
import sys

sys.path.append(os.path.abspath('../src'))

import Movement

class Test_Movement(unittest.TestCase):
	def setUp(self):
		self.speed = 30

		self.mock_senses = mock_Senses.mock_Senses()
		self.movement = Movement.Movement(mock_Motors.mock_Motors(), mock_Log.mock_Log(), self.mock_senses, self.speed)

	def test_move(self):
		direction = 1
		self.assertEqual(self.movement.move(direction), self.speed)

	def test_not_move(self):
		direction = 0
		self.assertFalse(self.movement.move(direction))

	def test_turn(self):
		direction = 1
		turn_time = 2
		self.assertEqual(int(self.movement.turn(direction, turn_time)), turn_time)

	def test_not_turn(self):
		direction = -1
		turn_time = 0
		self.assertEqual(self.movement.turn(direction, turn_time), turn_time)

	def test_not_turn_pressure(self):
		self.mock_senses.pressure_stopped = True
		direction = 1
		turn_time = 0
		self.assertEqual(self.movement.turn(direction, turn_time), 0)
		self.mock_senses.reset()

	def test_turn_degrees(self):
		degrees = 2
		self.assertAlmostEqual(int(self.movement.turndegrees(degrees)), degrees)

	def test_negative_turn_degrees(self):
		degrees = -2
		self.assertAlmostEqual(int(self.movement.turndegrees(degrees)), degrees)

	def test_zero_turn_degrees(self):
		degrees = 0
		self.assertEqual(self.movement.turndegrees(degrees), degrees)

	def test_stop(self):
		self.assertEqual(self.movement.stop(), 0)

if __name__=='__main__':
	unittest.main()
