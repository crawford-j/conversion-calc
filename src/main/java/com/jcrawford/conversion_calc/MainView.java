package com.jcrawford.conversion_calc;

import ch.qos.logback.core.Layout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.math.BigInteger;


@Route("")
public class MainView extends VerticalLayout {
    // Declare layouts and spans
    private VerticalLayout resultLayout;
    private Span resultValue;
    private Span resultMessage;
    private Span resultConversion;

    public MainView() {
        // Set background color to dark by default
        setHeight("100vh");
        getStyle().set("background-color", "#353755");
        getStyle().set("overflow", "auto");

        // Create inner layout for centering content
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setHeight("100vh");
        mainLayout.setAlignItems(Alignment.CENTER);

        // Create title at top-middle of page
        H1 title = new H1("Conversion Calculator created by Justen Crawford");
        title.getStyle().set("color", "#E0E4EE");
        title.getStyle().set("text-align", "center");
        title.getStyle().set("font-weight", "bold");
        mainLayout.add(title);

        // Horizontal layout for "Convert" and ComboBox
        HorizontalLayout convertLayout = new HorizontalLayout();

        // Create the text "Convert"
        Span convertText = new Span("Convert");
        convertText.getStyle().set("font-weight", "bold");
        convertText.getStyle().set("font-size", "25px");
        convertText.getStyle().set("color", "#E0E4EE");


        // Create the text "to"
        Span toText = new Span("to");
        toText.getStyle().set("font-size", "25px");
        toText.getStyle().set("font-weight", "bold");
        toText.getStyle().set("color", "#E0E4EE");


        // Create ComboBoxes for conversion option selection
        ComboBox<String> inputOption = new ComboBox<>();
        inputOption.getStyle().set("background-color", "#FF7F50");
        inputOption.getStyle().set("box-shadow", "none");
        inputOption.getStyle().set("border", "none");
        inputOption.getStyle().set("outline", "none");
        inputOption.setWidth("19%");
        inputOption.getStyle().set("font-size", "25px");
        inputOption.getStyle().set("color", "#E0E4EE");
        inputOption.setItems("binary","octal", "decimal", "hexadecimal");
        inputOption.setPlaceholder("Choose"); // Default placeholder

        ComboBox<String> resultOption = new ComboBox<>();
        resultOption.getStyle().set("background-color", "#FF7F50");
        resultOption.getStyle().set("box-shadow", "none");
        resultOption.getStyle().set("border", "none");
        resultOption.setWidth("19%");
        resultOption.getStyle().set("font-size", "25px");
        resultOption.getStyle().set("color", "#E0E4EE");
        resultOption.setItems("binary","octal", "decimal", "hexadecimal");
        resultOption.setPlaceholder("Choose"); // Default placeholder

        // Add components to the layout
        convertLayout.add(convertText, inputOption, toText, resultOption);

        // Align layout to the right
        convertLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        convertLayout.setWidthFull();

        // Create a spacer
        Span spacer = new Span();
        spacer.setHeight("100px");
        mainLayout.add(spacer);

        // Add convert layout to the main layout
        mainLayout.add(convertLayout);

        // Create a spacer
        Span spacer2 = new Span();
        spacer2.setHeight("10px");
        mainLayout.add(spacer2);

        // Create user input layout
        HorizontalLayout inputLayout = new HorizontalLayout();

        // Create user input text field
        TextField inputText = new TextField(inputOption.getValue());
        inputText.setWidth("1000px");
        inputText.getStyle().set("background-color", "#E0E4EE");
        inputText.getStyle().set("color", "#000000");
        inputText.getStyle().set("font-size", "25px");
        inputText.setPlaceholder("Enter value to be converted"); // Default placeholder

        // Update placeholder based on Combo box selection
        inputOption.addValueChangeListener(e -> {
            String selectedValue = inputOption.getValue();
            if(selectedValue != null) {
                inputText.setPlaceholder("Enter " + selectedValue + " value");
            }
        });

        // Adjust the inputLayout formatting and add
        inputLayout.add(inputText);
        inputLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        inputLayout.setWidthFull();
        mainLayout.add(inputLayout);

        // Create Convert button
        Button convertButton = new Button("Convert");
        convertButton.getStyle().set("background-color", "#FF7F50");
        convertButton.getStyle().set("color", "#E0E4EE");
        convertButton.getStyle().set("font-size", "25px");
        convertButton.setHeight("5%");
        convertButton.setWidth("30%");
        mainLayout.add(convertButton);

        // Initialize result layout and spans
        resultLayout = new VerticalLayout();
        resultValue = new Span();
        resultMessage = new Span();
        resultConversion = new Span();

        // Set styles for spans
        resultValue.getStyle().set("color", "#E0E4EE");
        resultValue.getStyle().set("font-size", "25px");
        resultValue.getStyle().set("font-weight", "bold");
        resultValue.getStyle().set("overflow-wrap", "break-word"); // Enable text wrapping
        resultValue.getStyle().set("white-space", "normal"); // Allow wrapping
        resultValue.getStyle().set("width", "100%"); // Full width for the span

        resultMessage.getStyle().set("color", "#E0E4EE");
        resultMessage.getStyle().set("font-size", "25px");
        resultMessage.getStyle().set("font-weight", "bold");
        resultMessage.getStyle().set("overflow-wrap", "break-word"); // Enable text wrapping
        resultMessage.getStyle().set("white-space", "normal"); // Allow wrapping
        resultMessage.getStyle().set("width", "100%"); // Full width for the span

        resultConversion.getStyle().set("color", "#E0E4EE");
        resultConversion.getStyle().set("font-size", "25px");
        resultConversion.getStyle().set("font-weight", "bold");
        resultConversion.getStyle().set("overflow-wrap", "break-word"); // Enable text wrapping
        resultConversion.getStyle().set("white-space", "normal"); // Allow wrapping
        resultConversion.getStyle().set("width", "100%"); // Full width for the span

        // Add spans and adjust formatting of layout
        resultLayout.add(resultValue, resultMessage, resultConversion);
        resultLayout.setAlignItems(Alignment.CENTER);
        resultLayout.getStyle().set("max-width", "600px");
        resultLayout.getStyle().set("overflow-wrap", "break-word");
        resultLayout.getStyle().set("text-align", "center");
        mainLayout.add(resultLayout);

        // Add main layout
        add(mainLayout);

        // On button press, display result
        convertButton.addClickListener(e -> {

            // Check if input is valid based on selected option
            if (!isInputValid(inputOption.getValue(), inputText.getValue())) {
                resultValue.setText("Invalid input for " + inputOption.getValue() + " option");
                resultMessage.setText("");
                resultConversion.setText("");
                return; // Exit the click listener if the input is invalid
            }

            // Set result text
            if (inputOption.getValue() != null && resultOption.getValue() != null) {
                String base1;
                String base2;
                switch (inputOption.getValue()) { // Get and set base from input option
                    case "binary":
                        base1 = ")\u2082"; // subscript 2
                        break;
                    case "octal":
                        base1 = ")\u2088"; // subscript 8
                        break;
                    case "decimal":
                        base1 = ")\u2081\u2080"; // subscript 10
                        break;
                    case "hexadecimal":
                        base1 = ")\u2081\u2086"; // subscript 16
                        break;
                    default:
                        base1 = ")";
                }
                switch (resultOption.getValue()) { // Get and set base from result option
                    case "binary":
                        base2 = ")\u2082"; // subscript 2
                        break;
                    case "octal":
                        base2 = ")\u2088"; // subscript 8
                        break;
                    case "decimal":
                        base2 = ")\u2081\u2080"; // subscript 10
                        break;
                    case "hexadecimal":
                        base2 = ")\u2081\u2086"; // subscript 16
                        break;
                    default:
                        base2 = ")";
                }

                // Perform conversion
                BigInteger decimalValue = convertToDecimal(inputText.getValue(), inputOption.getValue());
                String convertedValue = convertFromDecimal(decimalValue, resultOption.getValue());

                // Set result spans
                resultValue.setText("(" + inputText.getValue() + base1);
                resultMessage.setText(" converted to " + resultOption.getValue() + " is: ");
                resultConversion.setText("(" + convertedValue + base2);



            } else {

                resultValue.setText("Please select valid options and enter a value");
                resultMessage.setText("");
                resultConversion.setText("");

            }
        });
        // Center the title vertically
        mainLayout.setAlignItems(Alignment.CENTER);
    }

    private boolean isInputValid(String inputOption, String inputText) {
        if (inputText.isEmpty()) {
            return false;
        }
        switch (inputOption) {
            case "binary":
                return inputText.matches("[01]+"); // Only 0s and 1s
            case "octal":
                return inputText.matches("[0-7]+"); // Only 0-7
            case "decimal":
                return inputText.matches("\\d+"); // Only digits
            case "hexadecimal":
                return inputText.matches("[0-9a-fA-F]+"); // 0-9, A-F (case sensitive)
            default:
                return false;
        }

    }

    private BigInteger convertToDecimal(String input, String inputOption) {
        switch (inputOption) {
            case "binary":
                return new BigInteger(input, 2);
            case "octal":
                return new BigInteger(input, 8);
            case "decimal":
                return new BigInteger(input); // base 10
            case "hexadecimal":
                return new BigInteger(input, 16);
            default:
                throw new IllegalArgumentException("Invalid input option");
        }
    }

    private String convertFromDecimal(BigInteger decimalVal, String resultOption) {
        switch (resultOption) {
            case "binary":
                return decimalVal.toString(2);
            case "octal":
                return decimalVal.toString(8);
            case "decimal":
                return decimalVal.toString();
            case "hexadecimal":
                return decimalVal.toString(16).toUpperCase();
            default:
                throw new IllegalArgumentException("Invalid input option");
        }
    }
}
