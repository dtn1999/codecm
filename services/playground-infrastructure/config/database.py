from pymongo import MongoClient
from pymongo.collection import Collection

from env_loader import config

DB_URI = config["DB_URI"]
DB_NAME = config["DB_NAME"]
DB_COLLECTION = config["DB_COLLECTION"]

mongo_client = MongoClient(DB_URI)
mongo_db = mongo_client[DB_NAME]
playground_collection: Collection = mongo_db[DB_COLLECTION]
