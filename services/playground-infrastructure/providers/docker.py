from common.services import ResourceProvider
from playground.model import Playground


class DockerProvider(ResourceProvider):
    """
    DockerProvider is a resource provider that uses Docker to create playgrounds.
   """

    def __init__(self):
        super().__init__()

    def create_playground(self, playground: Playground) -> Playground:
        pass

    def get_playground(self, playground_id: str) -> Playground:
        pass

    def get_all_playgrounds(self) -> list[Playground]:
        pass

    def restore_playground(self, playground: Playground) -> Playground:
        pass

    def delete_playground(self, playground_id: str) -> None:
        pass
