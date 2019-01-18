package controlcenter.web.errors

import controlcenter.domain.CommandHistoryDomain

class HistoryLostCommandException(entity: CommandHistoryDomain)
    : Exception("History domain with id=${entity.id} and text=${entity.commandText} dont link with any command")