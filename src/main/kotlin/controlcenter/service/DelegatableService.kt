package controlcenter.service

abstract class DelegatableService<T>(private val allServices: Collection<T>) {
    val otherServices: Collection<T> = allServices
            .filter { service -> service != this }
            .let { services ->
                if (this is Prioritized) services.sortedBy { service -> (service as Prioritized).executionPriority }
                else services
            }
}