class mock_Log():
    def __init__ (self):
        return

    def write(self, msg):
        # This is a mock and thus does not write anything.
        return

    def error(self, msg):
        self.write("Error:\t\t\t" + msg)

    def warn(self, msg):
        self.write("Warning:\t\t" + msg)

    def info(self, msg):
        self.write("Information:\t" + msg)
