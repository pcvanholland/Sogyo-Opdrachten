package taipan.domain;

class InvalidCardException extends IllegalArgumentException
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
