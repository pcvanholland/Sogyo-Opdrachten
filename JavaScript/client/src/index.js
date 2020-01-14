
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

for (let element of document.querySelectorAll("button"))
{
    element.addEventListener("click", orderButtonClicked);
}

saveOrderInShoppingBasket = function(nameOfAttraction, numberOfAdults, numberOfChilderen)
{
    for (let i = 0; true; ++i)
    {
        if (localStorage.getItem(nameOfAttraction + "_" + i))
        {
            continue;
        }

        localStorage.setItem(nameOfAttraction + "_" + + i,
            "NA: " + numberOfAdults + ",NC: " + numberOfChilderen);
        break;
    }

    updateShoppingBasketBadge();
}
