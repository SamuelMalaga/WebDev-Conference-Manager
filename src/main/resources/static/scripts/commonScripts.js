function displayRegistrationAmount(){

    var themesElement = document.getElementById("themeIds");
    var activitiesElement = document.getElementById("activityIds");
    var participantPrice = 0;
    var companionPrice = 0;
    var companionsQuantity = document.getElementById("companionNumber")
    for(const selectedElement of activitiesElement.selectedOptions){
        const extractedPrices = extractPrices(selectedElement.text);
        participantPrice += extractedPrices.price;
        companionPrice += extractedPrices.companionPrice;
    }
    var companionsFinalPrice = companionPrice * companionsQuantity.value;

    var totalPrice = companionsFinalPrice + participantPrice;

    if(
    confirm("This is a confirmation message \n"
    +
        "Number of companions " + companionsQuantity.value +" \n"
    +
        "Price of activites: " + participantPrice +" \n"
    +
        "Price of companion activities: " + companionsFinalPrice +" \n"
    +
        "Total amount: " + totalPrice +" \n") === true){
        console.log("confirmed");
    } else {
        console.log("not confirmed");
    }
}

function extractPrices(text) {
    const regex = /Price (\d+\.\d{1,2}) \| CompanionPrice (\d+\.\d{1,2})/;
    const match = text.match(regex);

    if (match) {
        return {
            price: parseFloat(match[1]),  // Extract Price
            companionPrice: parseFloat(match[2])  // Extract Companion Price
        };
    } else {
        return null; // Return null if no match is found
    }
}

document.getElementById("activityIds").addEventListener("change", function() {
    // Get all selected options
    let select = this;
    let selectedOptions = Array.from(select.selectedOptions);

    // Initialize total values
    var participantPrice = 0;
    var companionPrice = 0;
    var companionsQuantity = document.getElementById("companionNumber");


    for(const selectedElement of selectedOptions){
        const extractedPrices = extractPrices(selectedElement.text);
        participantPrice += extractedPrices.price;
        companionPrice += extractedPrices.companionPrice;
    }

    var companionsFinalPrice = companionPrice * companionsQuantity.value;

    var totalPrice = companionsFinalPrice + participantPrice;

    document.getElementById("registrationAmount").textContent = totalPrice + " €";
});

document.getElementById("companionNumber").addEventListener("input", function() {
    // Get the select element
    var activitiesElement = document.getElementById("activityIds");

    // Clear all selected options
    for (var i = 0; i < activitiesElement.options.length; i++) {
        activitiesElement.options[i].selected = false;
    }
    document.getElementById("registrationAmount").textContent = "0 €";
});


function filterTable() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("entityDisplayTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}