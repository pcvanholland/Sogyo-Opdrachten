
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
    let orderSucceeded;

// https://stackoverflow.com/questions/29775797/fetch-post-json-data
(async() => {
  const rawResponse = await fetch("/api/placeorder", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(localStorage)
  });
  const content = await rawResponse;
})();

    if (orderSucceeded)
    {
        localStorage.clear();
        window.location.replace("orderplaced.html");
    }
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
    let numberOfKids = parseInt(data.numberOfKids || 0, 10);

    let template = document.getElementById("tickettemplate");
    let ticket = template.content.cloneNode(true);

    let item = ticket.querySelector("div");

    item.innerHTML = parkName;
    item = item.nextElementSibling;

    item.innerHTML += numberOfAdults;
    item = item.nextElementSibling;

    item.innerHTML += numberOfKids;

    button = item.nextElementSibling;
    button.referenceElement = element;
    button.addEventListener("click", cancelOrderButtonClicked);

    document.body.insertBefore(ticket, document.getElementById("paynowbutton"));
}

document.getElementById("finalisepaymentbutton").addEventListener("click", orderNowButtonClicked);
