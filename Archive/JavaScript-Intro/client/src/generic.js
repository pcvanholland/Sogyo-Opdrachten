window.onload = function()
{
    updateShoppingBasketBadge();
}

updateShoppingBasketBadge = function()
{
    document.getElementById("shoppingbasketbadge").innerText = localStorage.length;
}
