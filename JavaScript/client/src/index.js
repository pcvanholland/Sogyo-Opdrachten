
orderButtonClicked = function(msg)
{
    console.log("called!");

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
    console.log(nameOfAttraction);
    if (!nameOfAttraction)
    {
        return;
    }

    let numberOfAdults = 0;
    let numberOfChildren = 0;
    // Ugly hardcoding of the path ;(
    for (let element of msg.target.parentElement.attributes[0].ownerElement.children)
    {
        if (element.className === "numberofadults" && element.value >= 0)
        {
            numberOfAdults = element.value;
        }
        if (element.className === "numberofkids" && element.value >= 0)
        {
            numberOfChildren = element.value;
        }
    }
    console.log(numberOfAdults);
    console.log(numberOfChildren);

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
    console.log("Order saved.");
}
