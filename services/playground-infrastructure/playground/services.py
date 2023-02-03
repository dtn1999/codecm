from common.services import ResourceProvider
from playground.model import Playground


class PlaygroundService:
    provider: ResourceProvider

    def __init__(self, provider: ResourceProvider):
        self.provider = provider

    def create_playground(self, playground: Playground) -> Playground:
        return self.provider.create_playground(playground)

    def get_playground(self, playground_id: str) -> Playground:
        return self.provider.get_playground(playground_id)

    def get_all_playgrounds(self) -> list[Playground]:
        return self.provider.get_all_playgrounds()

    def restore_playground(self, playground: Playground) -> Playground:
        return self.provider.restore_playground(playground)

    def delete_playground(self, playground_id: str) -> None:
        self.provider.delete_playground(playground_id)
