import time

class Navigation():
    def __init__ (self, log, brickpi3, Movement, Senses):
        speed = 30
        self.update_interval = 0.2 # Seconds.
        self.minimum_degrees_to_turn = 1
        self.backup_time = 0.5 # Seconds.
        self.check_colour_timeout = 2 # Seconds.
        self.find_nav_colour_iterations = 0
        self.align_nav_colour_iterations = 0
        self.max_align_attempts = 3
        # We try to turn left first, for that is most often the case.
        self.turn_degrees_search_black = -15
        self.degrees_turned_while_looking_for_black = 0

        self.bp = brickpi3
        self.senses = Senses
        self.movement = Movement
        self.log = log


    def may_move_forward(self):
        if self.senses.pressure_stop():
            return False
        if self.senses.color_stop():
            return False
        if self.senses.distance_stop():
            return False
        return True


    def navigate(self):
        while(True):
            self.log.info("Nav: Navigate: Start of while loop.")

            self.try_move_forward()
            if(self.senses.pressure_stop()):
                self.move_from_obstacle()
            elif(self.senses.color_stop()):
                self.handle_colour_stop()
            elif(self.senses.distance_stop()):
                self.handle_distance_stop()
            else:
                self.log.warn("Nav: We stopped without apparent reason!")

            if(self.senses.finished()):
                if(self.handle_finish()):
                    self.log.info("Nav: Navigation: Apparently we've arrived, congratz =D")
                    break

        self.movement.stop()
        self.bp.reset_all()


    def start(self):
        try:
            self.navigate()

        except KeyboardInterrupt:
            self.movement.stop()
            self.bp.reset_all()


    def handle_finish(self):
        self.movement.moveforward()
        while(not self.senses.pressure_stop()):
            time.sleep(self.update_interval)
            self.senses.update_sensors()
        self.movement.stop()
        return True
        #self.try_to_turn(180)
        # self.movement.movebackward()
        # t = time.time()
        # self.senses.update_sensors()
        # while(not self.senses.finished() and time.time() - t < self.check_colour_timeout):
        #    time.sleep(self.update_interval)
        #    self.senses.update_sensors()

        # self.movement.stop()
        # return self.senses.is_navigation_color()


    def try_move_forward(self):
        t = time.time()
        self.movement.moveforward()
        self.senses.update_sensors()

        while(self.may_move_forward()):
            time.sleep(self.update_interval)
            self.senses.update_sensors()

        self.movement.stop()
        self.log.info("Mov: Navigate: Forward: " + str(time.time() - t))

        # By definition we've successfully moved,,,
        return True


    def handle_distance_stop(self):
        self.move_towards_gap()


    def move_towards_gap(self):
        return self.try_to_turn(45) and self.try_move_forward()


    # def move_from_wall(self):
    #     return self.try_to_turn(-10) and self.try_move_forward()


    def try_to_turn(self, degrees_to_turn):
        degrees_to_turn -= self.movement.turndegrees(degrees_to_turn)
        if(abs(degrees_to_turn)>self.minimum_degrees_to_turn):
            self.move_from_obstacle()
            return self.try_to_turn(degrees_to_turn)
        return True


    def move_from_obstacle(self):
        self.log.info("Nav: Navigate: move_from_obstacle")

        delta_angle = 10

        was_pressed_left = self.senses.get_pressed_left()
        was_pressed_right =  self.senses.get_pressed_right()
        self.movement.movebackward()
        t = time.time()
        self.senses.update_sensors()
        while(self.senses.pressure_stop()):
            time.sleep(self.update_interval)
            self.senses.update_sensors()

        self.movement.stop()
        self.log.info("Mov: Navigate: Backward: " + str(time.time() - t))

        modifier = 1 if self.senses.seen_avoid_color_recently() else -1

        if(was_pressed_right and not was_pressed_left):
            self.log.info("Nav: Navigate: Pressed right, not left.")
            return self.try_to_turn(-delta_angle * modifier)

        elif(was_pressed_left and not was_pressed_right):
            self.log.info("Nav: Navigate: Pressed left, not right.")
            return self.try_to_turn(delta_angle * modifier)
        elif(was_pressed_left and was_pressed_right):
            self.log.info("Nav: Navigate: Pressed both.")
            if (self.senses.has_gap_on_right_side()):
                return self.try_to_turn(90)
            else:
                return self.try_to_turn(-90)
        else:
            self.log.warn("Nav: Navigate: move_from_obstacle called when pressed neither.")
            return True


    def backup(self):
        self.log.info("Nav: Navigate: Backing up.")
        self.movement.movebackward()
        time.sleep(self.backup_time)
        self.movement.stop()


    def handle_colour_stop(self):
        self.log.info("Nav: Navigate: handle_colour_stop")
        self.senses.update_sensors()
        if(self.senses.is_avoid_color()):
            return self.move_from_avoid_colour()
        elif(self.senses.is_navigation_color()):
            return self.align_to_navigation_colour()


    def align_to_navigation_colour(self):
        self.log.info("Nav: Navigate: align_to_navigation_colour.")

        turn_degrees = 10
        time_forward = self.align_to_navigation_colour_step(0)
        if(not time_forward):
            return False
        if(self.align_nav_colour_iterations>=self.max_align_attempts):
            self.movement.moveforward()
            time.sleep(time_forward)
            self.movement.stop()
            return False
        time_right_1 = self.align_to_navigation_colour_step(turn_degrees)
        if(not time_right_1):
            return False
        time_right_2 = self.align_to_navigation_colour_step(turn_degrees)
        if(not time_right_2):
            return False
        time_right_3 = self.align_to_navigation_colour_step(turn_degrees)
        if(not time_right_3):
            return False
        time_left_1 = self.align_to_navigation_colour_step(-4 * turn_degrees)
        if(not time_left_1):
            return False
        time_left_2 = self.align_to_navigation_colour_step(-turn_degrees)
        if(not time_left_2):
            return False
        time_left_3 = self.align_to_navigation_colour_step(-turn_degrees)
        if(not time_left_3):
            return False
        list_directions = [time_left_3, time_left_2, time_left_1, time_forward, \
            time_right_1, time_right_2, time_right_3]
        duration_threshold = self.update_interval
        maximum_duration = 4

        if (duration_threshold>max(list_directions)):
            self.log.info("Nav: Navigate: Time spent moving over nav. colour was too low.")
            self.log.info("Nav: Navigate: Aligned forwards.")
            self.try_to_turn(2 * turn_degrees)
            return self.move_out_of_colour(self.senses.navigation_color)
        else:
            self.log.info("Nav: Navigate: Aligned.")
            self.try_to_turn(list_directions.index(max(list_directions)) * turn_degrees)

        self.senses.update_sensors()
        self.log.info("Nav: Navigate: Aligned and seeing the colour: " + self.senses.get_color())

        if(self.senses.get_color() == self.senses.navigation_color):
            self.move_out_of_colour(self.senses.navigation_color)
            #turning as compensation
            self.try_to_turn(-5)
        elif(maximum_duration<max(list_directions)):
            self.log.info("Nav: Navigate: Aligned but not seeing the navigation_colour so moving forward ~3 sec.")
            self.movement.moveforward()
            time.sleep(self.update_interval * 15)
            self.movement.stop()
        else:
            self.try_to_turn(-5)
        self.align_nav_colour_iterations += 1
        return self.senses.get_color() != self.senses.avoid_color


    def align_to_navigation_colour_step(self, turn_degrees):
        self.try_to_turn(turn_degrees)
        duration = self.time_to_move_over_navigation_colour()
        self.log.info("Nav: Navigate: Tried aligning for: " + str(duration))
        if (not duration):
            self.log.info("Nav: Navigate: Aborted aligning.")
            self.move_out_of_colour(self.senses.avoid_color)
            if(self.senses.finished()):
                return False
            self.move_forward_time_or_wall(5)
            self.align_nav_colour_iterations = 0
            self.find_navigation_colour()
            return False
        return duration


    def time_to_move_over_navigation_colour(self):
        self.log.info("Nav: Navigate: time_to_move_over_navigation_color.")
        start_time = time.time()
        if (not self.move_out_of_colour(self.senses.navigation_color)):
            self.log.info("Nav: Navigate: Encountered Red during nav-colour scanning.")
            return False

        duration = time.time() - start_time
        self.movement.movebackward()
        t = time.time()
        time.sleep(duration)
        self.movement.stop()
        self.log.info("Mov: Navigate: Backward: " + str(time.time() - t))
        return duration


    def move_out_of_colour(self, colour):
        self.log.info("Nav: Navigate: move_out_of_colour: " + colour)
        t = time.time()
        # Smaller update interval here, for else we could be moving over the colour too fast.
        move_out_of_colour_update_interval = self.update_interval/10

        # We can't use try to move forward here, for we
        # need a real time update of the colour.
        self.movement.moveforward()
        time.sleep(move_out_of_colour_update_interval)
        self.senses.update_sensors()
        while(self.senses.get_color() == colour and not self.senses.pressure_stop()):
            time.sleep(move_out_of_colour_update_interval)
            self.senses.update_sensors()
        self.movement.stop()
        self.log.info("Mov: Navigate: Forward: " + str(time.time() - t))
        return self.senses.get_color() != self.senses.avoid_color


    def move_from_avoid_colour(self):
        self.log.info("Nav: Navigate: move_from_avoid_colour")
        compensation = 10

        self.movement.turndegrees(-15)
        if not self.senses.color_stop():
            return True
        else:
            self.movement.turndegrees(30)
            if not self.senses.color_stop():
                return True
        self.movement.turndegrees(-15)

        degrees_right = self.turn_from_avoid_colour(True)
        degrees_left = self.turn_from_avoid_colour(False)

        if not degrees_left or not degrees_right:
            return True

        if degrees_right >= 90 and degrees_right >= 90:
            self.movement.turndegrees(-90)

        if degrees_right > degrees_left:
            self.try_to_turn(degrees_right + degrees_left + compensation)
        else:
            self.try_to_turn(-compensation)
        return True


    def turn_from_avoid_colour(self, is_turn_right):
        turned_degrees = 0
        delta = 3 if is_turn_right else -3
        multiplier = 5
        self.log.info("Nav: Navigate: turn_from_avoid_colour, turning right is: " + str(is_turn_right) + " delta: " + str(delta))
        self.senses.update_sensors()
        self.movement.turndegrees(multiplier * delta)
        turned_degrees +=(multiplier * delta)
        self.senses.update_sensors()
        while(self.senses.color_stop() and not self.senses.pressure_stop() and turned_degrees <= 90):
            self.movement.turndegrees(delta)
            turned_degrees += delta
            self.senses.update_sensors()
        if self.senses.pressure_stop():
            self.log.info("Nav: Navigate: Pressure stop in red.")
            if self.senses.has_gap_on_right_side_while_in_red():
                self.log.info("Nav: Navigation: Turning: " + str(-turned_degrees + 90))
                self.try_to_turn(-turned_degrees + 90)
            else:
                self.try_to_turn(-turned_degrees - 90)
            return False
        return turned_degrees


    def find_navigation_colour(self):
        # eindigen op zwart, dan neemt navigate correct over
        self.log.info("Nav: Navigate: find_navigation_colour")
        # We can't use try to move forward here, for we
        # need a real time update of the colour and pressure.
        t = time.time()
        max_find_time = abs(self.movement.speed*0.09)
        self.movement.moveforward()
        self.senses.update_sensors()

        while(self.senses.get_color() != self.senses.navigation_color \
                and not self.senses.pressure_stop() and self.senses.get_color() != self.senses.avoid_color) \
                and (time.time() - t) < max_find_time:
            time.sleep(self.update_interval)
            self.senses.update_sensors()
            self.log.info("Looking for black, currently seeing: " + self.senses.get_color())

        if (self.senses.get_color() == self.senses.navigation_color):
            self.log.info("self.find_nav_colour_iterations: " + str(self.find_nav_colour_iterations))
            self.find_nav_colour_iterations += 1
            if(self.find_nav_colour_iterations == 2):
                self.degrees_turned_while_looking_for_black = 0
                self.find_nav_colour_iterations = 0
                return True
            self.align_to_navigation_colour()
            if not self.move_forward_time_or_wall(5):
                return True
            return self.find_navigation_colour()
        elif (self.senses.pressure_stop() or self.senses.get_color() == self.senses.avoid_color):
            self.log.info("Looking for black but found pressure or Red colour, moving backwards")
            if self.go_find_return_turn(t):
                return True
            self.find_navigation_colour()
        elif not (time.time() - t) < max_find_time:
            if self.go_find_return_turn(t):
                return True
            self.find_navigation_colour()
        else:
            self.log.warn("Something unexpected happened in find_navigation_colour.")

    def go_find_return_turn(self, t):
        time_moved_forward = time.time() - t
        self.movement.stop()
        self.movement.movebackward()
        time.sleep(time_moved_forward)
        self.movement.stop()
        self.try_to_turn(self.turn_degrees_search_black)
        self.degrees_turned_while_looking_for_black += self.turn_degrees_search_black
        if (self.degrees_turned_while_looking_for_black == self.turn_degrees_search_black*7):
            if self.degrees_turned_while_looking_for_black > 0:
                self.degrees_turned_while_looking_for_black = 0
                self.turn_degrees_search_black *= -1
                return True
            else:
                self.turn_degrees_search_black *= -1
                self.try_to_turn(- self.degrees_turned_while_looking_for_black)
                self.degrees_turned_while_looking_for_black = 0

    def move_forward_time_or_wall(self, t):
        self.log.info("Nav: Navigate: Started move_forward_time_or_wall for: "+str(t))
        self.movement.moveforward()
        start_time = time.time()
        while (time.time() - start_time) < t*self.update_interval:
            time.sleep(0.1*self.update_interval)
            self.senses.update_sensors()
            if self.senses.pressure_stop():
                self.movement.stop()
                return False
        self.movement.stop()
        return True
