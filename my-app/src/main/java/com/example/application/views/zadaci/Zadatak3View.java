package com.example.application.views.zadaci;

import com.example.application.model.Kolegij;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


@PageTitle("Zadatak 3")
@Route(value = "zadatak3", layout = MainLayout.class)
public class Zadatak3View extends VerticalLayout {

    private H1 naslovH1;
    private ComboBox<String> imeKolegijaComboBox;
    private ComboBox<Integer> ocjenaComboBox;
    private Button potvrdiButton;
    private Grid<Kolegij> kolegijiGrid;
    private Div prosjekDiv;

    private List<Kolegij> kolegiji = new ArrayList<>();

    public Zadatak3View() {
        createComponents();
        composeView();
    }

    private void createComponents() {
        naslovH1 = new H1("Kolegiji i ocjene - Dodavanje ComboBox-a i izmjena layout-a");

        imeKolegijaComboBox = new ComboBox<>("Izaberite kolegij:");
        imeKolegijaComboBox.setWidth("300px");
        imeKolegijaComboBox.setItems("Informatika", "Organizacija", "Matematika 1", "Programiranje 1", "Modeliranje poslovnih procesa", "Web dizajn i programiranje", "Algoritmi");

        ocjenaComboBox = new ComboBox<>("Izaberite ocjenu:");
        ocjenaComboBox.setWidth("200px");
        ocjenaComboBox.setItems(1, 2, 3, 4, 5);

        potvrdiButton = new Button("Potvrdi");
        potvrdiButton.addClickListener(e -> {
            String kolegij = imeKolegijaComboBox.getValue();
            String ocjena = ocjenaComboBox.getValue().toString();

            if (!kolegij.isEmpty() && isOcjenaValid(ocjena)) {
                int ocjenaBroj = Integer.parseInt(ocjena);
                kolegiji.add(new Kolegij(kolegij, ocjenaBroj));
                kolegijiGrid.setItems(kolegiji);
                updateProsjek();
                imeKolegijaComboBox.clear();
                ocjenaComboBox.clear();
            } else {
                Notification.show("Molimo unesite ispravne podatke.");
            }
        });

        kolegijiGrid = new Grid<>(Kolegij.class);
        kolegijiGrid.setColumns("imeKolegija", "ocjena");
        kolegijiGrid.setItems(kolegiji);

        prosjekDiv = new Div();

        updateProsjek();
    }

    private void composeView() {
        add(naslovH1);
        add(new HorizontalLayout(imeKolegijaComboBox, ocjenaComboBox));
        add(potvrdiButton);
        add(kolegijiGrid);
        add(prosjekDiv);
    }

    private void updateProsjek() {
        double ukupniProsjek = kolegiji.stream().mapToInt(Kolegij::getOcjena).average().orElse(0.0);
        prosjekDiv.setText("Ukupni prosjek ocjena: " + String.format("%.2f", ukupniProsjek));
    }

    private boolean isOcjenaValid(String ocjenaText) {
        try {
            int ocjena = Integer.parseInt(ocjenaText);
            return ocjena >= 1 && ocjena <= 5;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
