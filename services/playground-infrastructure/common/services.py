from abc import ABC, abstractmethod

from playground.model import Playground


class ResourceProvider(ABC):
    """
    ResourceProvider is an abstract class that defines the interface for all the resource providers.
    the application will use to create playgrounds.
    """

    def __init__(self):
        super().__init__("ResourceProvider")

    @abstractmethod
    def create_playground(self, playground: Playground) -> Playground:
        """
        create_playground creates a playground using the given playground definition.
        :param playground: the definition of the playground to create.
        :return: the playground instance created.
        """
        pass

    @abstractmethod
    def get_playground(self, playground_id: str) -> Playground:
        """
        get_playground returns the playground instance with the given ID.
        :param playground_id: the ID of the playground to return.
        :return: the playground instance with the given ID.
        """
        pass

    @abstractmethod
    def get_all_playgrounds(self) -> list[Playground]:
        """
        get_all_playgrounds returns all the playground instances.
        :return: all the playground instances.
        """
        pass

    @abstractmethod
    def restore_playground(self, playground: Playground) -> Playground:
        """
        restore_playground restores the given playground.
        :param playground: the playground to restore.
        :return: the restored playground.
        """
        pass

    @abstractmethod
    def delete_playground(self, playground_id: str) -> None:
        """
        delete_playground deletes the playground with the given ID.
        :param playground_id: the ID of the playground to delete.
        :return: None
        """
        pass
