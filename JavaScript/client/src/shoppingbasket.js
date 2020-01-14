
window.onload = function()
{
    updateShoppingBasket();
    updateShoppingBasketBadge();
}

cancelOrderButtonClicked = function(msg)
{
    localStorage.removeItem(msg.target.referenceElement);

    window.location.reload(true);
}

orderNowButtonClicked = function(msg)
{
    localStorage.clear();

    window.location.replace("orderplaced.html");
}

updateShoppingBasket = function()
{
    for (let element in localStorage)
    {
        if (localStorage.getItem(element))
        {
            addItemToShoppingBasket(element);
        }
    }

    console.log("Updated shopping basket!");
}

addItemToShoppingBasket = function(element)
{
    data = localStorage.getItem(element)
    let parkName = element.split("_")[0];
    let numberOfAdults = parseInt(data.split(",")[0].substring(4), 10);
    let numberOfChildren = parseInt(data.split(",")[1].substring(4), 10);

    let template = document.getElementById("tickettemplate");
    let ticket = template.content.cloneNode(true);

    let item = ticket.querySelector("div");

    item.innerHTML = parkName;
    item = item.nextElementSibling;

    item.innerHTML += numberOfAdults;
    item = item.nextElementSibling;

    item.innerHTML += numberOfChildren;

    button = item.nextElementSibling;
    button.referenceElement = element;
    button.addEventListener("click", cancelOrderButtonClicked);

    document.body.insertBefore(ticket, document.getElementById("paynowbutton"));
}

document.getElementById("paynowbutton").addEventListener("click", orderNowButtonClicked);
