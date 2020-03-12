package taipan.domain;

class CantPlayException extends TaiPanException
{
    CantPlayException()
    {
        super();
    }
}

class CantPlayTableException extends CantPlayException
{
    CantPlayTableException()
    {
        super();
    }
}

class CantPlayPlayerException extends CantPlayException
{
    CantPlayPlayerException()
    {
        super();
    }
}
