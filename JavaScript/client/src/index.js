
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

    item = themeParkNode.getElementsByClassName("orderbutton")[0];
    item.addEventListener("click", orderButtonClicked);

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
    let numberOfChildren = 0;
    // Ugly hardcoding of the path ;(
    for (let element of msg.target.parentElement.attributes[0].ownerElement.children)
    {
        if (element.className === "numberofadults" && parseInt(element.value) >= 0)
        {
            numberOfAdults = parseInt(element.value, 10);
        }
        if (element.className === "numberofkids" && parseInt(element.value) >= 0)
        {
            numberOfChildren = parseInt(element.value, 10);
        }
    }

    if (numberOfAdults > 0 || numberOfChildren > 0)
    {
        saveOrderInShoppingBasket(nameOfAttraction, numberOfAdults, numberOfChildren);
    }
}

saveOrderInShoppingBasket = function(nameOfAttraction, numberOfAdults, numberOfChilderen)
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
            "numberOfChilderen": numberOfChilderen
        }));
        break;
    }

    updateShoppingBasketBadge();
}
