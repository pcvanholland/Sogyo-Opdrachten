
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
}

addItemToShoppingBasket = function(element)
{
    data = JSON.parse(localStorage.getItem(element));
    let parkName = data.nameOfAttraction;
    let numberOfAdults = parseInt(data.numberOfAdults || 0, 10) ;
    let numberOfChildren = parseInt(data.numberOfChildren || 0, 10);

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
