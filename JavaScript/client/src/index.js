
fetch("api/attractions")
  .then((response) => {
    return response.json();
  })
  .then((responsInJSON) => {
    addItemsToIndex(responsInJSON);
  });

addItemsToIndex = function(itemsInJSON)
{
    for (let themePark of itemsInJSON)
    {
        addItemToIndex(themePark);
    }
}

addItemToIndex = function(themeParkObject)
{
    let template = document.getElementById("parktemplate");
    let themeParkNode = document.importNode(template.content.querySelector("article"), true);

    let item = themeParkNode.getElementsByClassName("parkname")[0];
    item.innerHTML = themeParkObject.name;

    item = themeParkNode.getElementsByClassName("parkdescription")[0];
    item.innerText = themeParkObject.description;

    item = themeParkNode.getElementsByClassName("adultprice")[0].getElementsByClassName("price")[0];
    item.innerText = themeParkObject.adultPrice;

    item = themeParkNode.getElementsByClassName("kidsprice")[0].getElementsByClassName("price")[0];
    item.innerText = themeParkObject.kidsPrice;

    item = themeParkNode.getElementsByClassName("discountadults")[0];
    item.innerText = themeParkObject.minimumNumberOfAdults;

    item = themeParkNode.getElementsByClassName("discountkids")[0];
    item.innerText = themeParkObject.minimumNumberOfKids;

    item = themeParkNode.getElementsByClassName("discountpercentage")[0];
    item.innerText = themeParkObject.discount;

    item = themeParkNode.getElementsByClassName("numberofadults")[0];
    item.addEventListener("change", updatePricesInIndex);

    item = themeParkNode.getElementsByClassName("numberofkids")[0];
    item.addEventListener("change", updatePricesInIndex);

    item = themeParkNode.getElementsByClassName("orderbutton")[0];
    item.addEventListener("click", orderButtonClicked);
    if (themeParkObject.available < 1)
    {
        item.disabled = true;
    }

    document.body.appendChild(themeParkNode);
}

orderButtonClicked = function(msg)
{
    let nameOfAttraction;
    // Ugly hardcoding of the path ;(
    for (let element of msg.target.parentElement.parentElement.children)
    {
        if (element.className === "parkname")
        {
            nameOfAttraction = element.innerText;
            break;
        }
    }
    if (!nameOfAttraction)
    {
        return;
    }

    let numberOfAdults = 0;
    let numberOfKids = 0;
    // Ugly hardcoding of the path ;(
    for (let element of msg.target.parentElement.attributes[0].ownerElement.children)
    {
        if (element.className === "numberofadults" && parseInt(element.value) >= 0)
        {
            numberOfAdults = parseInt(element.value, 10);
        }
        if (element.className === "numberofkids" && parseInt(element.value) >= 0)
        {
            numberOfKids = parseInt(element.value, 10);
        }
    }

    if (numberOfAdults > 0 || numberOfKids > 0)
    {
        saveOrderInShoppingBasket(nameOfAttraction, numberOfAdults, numberOfKids);
    }

updatePricesInIndex();
}

saveOrderInShoppingBasket = function(nameOfAttraction, numberOfAdults, numberOfKids)
{
    for (let i = 0; true; ++i)
    {
        if (localStorage.getItem(i))
        {
            continue;
        }

        localStorage.setItem(i, JSON.stringify({
            "nameOfAttraction": nameOfAttraction,
            "numberOfAdults": numberOfAdults,
            "numberOfKids": numberOfKids
        }));
        break;
    }

    updateShoppingBasketBadge();
}

updatePricesInIndex = function()
{
    for (let parkEntry of document.getElementsByClassName("parkentry"))
    {
        let priceElement = parkEntry.getElementsByClassName("total")[0].getElementsByClassName("price")[0];

        let adultPricing = parseInt(parkEntry.getElementsByClassName("adultprice")[0].getElementsByClassName("price")[0].innerText, 10);
        let numberOfAdults = parseInt(parkEntry.getElementsByClassName("numberofadults")[0].value || 0, 10);

        let kidPricing = parseInt(parkEntry.getElementsByClassName("kidsprice")[0].getElementsByClassName("price")[0].innerText, 10);
        let numberOfKids = parseInt(parkEntry.getElementsByClassName("numberofkids")[0].value || 0, 10);

        let normalPrice = adultPricing * numberOfAdults + kidPricing * numberOfKids

        let discount = "";
        if (numberOfAdults >= parseInt(parkEntry.getElementsByClassName("discountadults")[0].innerText, 10) &&
            numberOfKids >= parseInt(parkEntry.getElementsByClassName("discountkids")[0].innerText, 10))
        {
            let percentage = parseInt(parkEntry.getElementsByClassName("discountpercentage")[0].innerText, 10);
            let discountValue = normalPrice * percentage / 100;
            discount += " - " + discountValue + " = " + (normalPrice - discountValue);
        }
        priceElement.innerText = normalPrice + discount;
    }
}
