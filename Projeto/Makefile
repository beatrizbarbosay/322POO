JAVA = java
JAVAC = javac
JAVAFX_PATH = ~/Downloads/openjfx-21.0.7_linux-x64_bin-sdk/javafx-sdk-21.0.7/lib
MODULES = --module-path $(JAVAFX_PATH) --add-modules javafx.controls,javafx.fxml
SRC = $(shell find src -name "*.java")
BIN = bin

build:
	$(JAVAC) $(MODULES) -d $(BIN) $(SRC)
	cp -r resources/* $(BIN)/

run: build
	$(JAVA) $(MODULES) -cp $(BIN) app.TelaLogin

clean:
	rm -rf $(BIN)/*