#!/usr/bin/env python
import Log
import Navigation
import brickpi3
import Movement
import Senses

log = Log.Log()
log.info("Started logging.")
log.error("Error of some kind.")
log.warn("Q.e.d.")

speed = 30

bp = brickpi3.BrickPi3()
log.info("Brickpi initialized.")

ss = Senses.Senses(bp, None)
log.info("Senses initialized.")

mm = Movement.Movement(bp, log, ss, speed)
log.info("Movement initialized.")

ss.set_movement(mm)

nav = Navigation.Navigation(log, bp, mm, ss)
log.info("Navigation initialized.")

nav.start()

log.info("Stopped logging.")
