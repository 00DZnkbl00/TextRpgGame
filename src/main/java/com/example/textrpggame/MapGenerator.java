package com.example.textrpggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MapGenerator {
    private File file;
    private Location[] locationsList = new Location[1];

    MapGenerator(String filePath) {
        file = new File(filePath);
    }

    public Location[] loadMapFromFile() {
        String input;
        String[] arguments;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                arguments = input.split("/");
                cleanArguments(arguments);
                if (arguments.length == 4 && "NL".equals(arguments[0])) {
                    loadLocation(arguments);
                } else if (arguments.length == 3 && "NP".equals(arguments[0])) {
                    conectLocations(arguments);
                } else {
                    System.out.println("Pominiento linie instrukcji");
                }
            }
            return locationsList;
        } catch (FileNotFoundException exception) {
            locationsList[0] = new Location("You are in void", "void","src/main/java/com/example/textrpggame/images/void.jpg");
            System.out.println("Błąd wczytywania pliku");
            return locationsList;
        }
    }

    private void cleanArguments(String[] arguments) {
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = arguments[i].trim();
        }
    }

    private void loadLocation(String[] arguments) {
        if (locationsList[0] == null) {
            locationsList[0] = new Location(arguments[1], arguments[2],"src/main/java/com/example/textrpggame/images/"+arguments[3]);
            System.out.println("Dodano lokacje  : " + arguments[2]);
        } else {
            for (int i = 0; i < locationsList.length; i++) {
                if (arguments[2].equals(locationsList[i].getShortInfo())) {
                    System.out.println("Ta lokacja już istnieje");
                    return;
                }
            }
            locationsList = Arrays.copyOf(locationsList, locationsList.length + 1);
            locationsList[locationsList.length - 1] = new Location(arguments[1], arguments[2],"src/main/java/com/example/textrpggame/images/"+arguments[3]);
            System.out.println("Dodano lokacje: " + arguments[2]);
        }
    }

    private void conectLocations(String[] arguments) {
        int fromLocationIndex = 0, toLocationIndex = 0;
        if (arguments[2].equals(arguments[1])) {
            System.out.println("Nie da się przejść do tej samej lokacji");
            return;
        }
        for (int i = 0; i < locationsList.length; i++) {
            if (arguments[2].equals(locationsList[i].getShortInfo())) {
                toLocationIndex = i;
                break;
            }
            if (i + 1 >= locationsList.length) {
                System.out.println("Brak lokacji do :" + arguments[2]);
                return;
            }
        }
        for (int i = 0; i < locationsList.length; i++) {
            if (arguments[1].equals(locationsList[i].getShortInfo())) {
                fromLocationIndex = i;
                break;
            }
            if (i + 1 >= locationsList.length) {
                System.out.println("Brak lokacji z :" + arguments[1]);
                return;
            }
        }
        locationsList[fromLocationIndex].addLocation(locationsList[toLocationIndex]);
        System.out.println("Uttorzono połączenie od: " + locationsList[toLocationIndex].getShortInfo()
                + " do " + locationsList[fromLocationIndex].getShortInfo());
    }
}
