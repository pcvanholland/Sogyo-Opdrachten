window.onload = function()
{
    updateShoppingBasketBadge();
}

updateShoppingBasketBadge = function()
{
    document.getElementById("shoppingbasketbadge").innerHTML = localStorage.length;
}
