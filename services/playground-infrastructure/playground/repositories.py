from config.database import playground_collection
from playground.model import Playground


class PlaygroundRepository:
    @staticmethod
    def get(self, playground_id: str) -> Playground:
        """Returns the playground instance with the given ID"""
        document = playground_collection.find_one({"_id": playground_id})
        if not document:
            return None
        return self.db[playground_id]