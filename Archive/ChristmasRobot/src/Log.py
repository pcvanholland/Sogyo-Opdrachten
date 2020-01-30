import time

class Log():
    def __init__ (self):
        i = 1
        while(i<5000000):
            try:
                self.logfile = "../logs/log_" + str(i) + ".log"
                f = open(self.logfile, "x")
                f.close()
                self.start_time = time.time()
                break
            except Exception as error:
                i += 1


    def write(self, msg):
        print(msg)
        f = open(self.logfile, "a")
        f.write("{:10.3f}".format(time.time() - self.start_time) + ": " + msg + "\n")
        f.close()

    def error(self, msg):
        self.write("Error:\t\t\t" + msg)

    def warn(self, msg):
        self.write("Warning:\t\t" + msg)

    def info(self, msg):
        self.write("Information:\t" + msg)
