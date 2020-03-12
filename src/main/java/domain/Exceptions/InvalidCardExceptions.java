package taipan.domain;

class InvalidCardException extends TaiPanException
{
    InvalidCardException()
    {
        super();
    }
}

class InvalidRankException extends InvalidCardException
{
    InvalidRankException()
    {
        super();
    }
}
