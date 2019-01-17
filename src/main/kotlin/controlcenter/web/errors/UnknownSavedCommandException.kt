package controlcenter.web.errors

import controlcenter.domain.CommandDomain

class UnknownSavedCommandException(entity: CommandDomain)
    : Exception("Can't find direct implementation of Command for exec type '${entity.execType}' (id=$entity.id)")