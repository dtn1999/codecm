import uuid
from typing import Optional
from pydantic import BaseModel, Field

from common.helper import get_time

_unix_ts = dict(example=get_time())
"""Common attributes for all Unix timestamp fields"""


class PlaygroundFields:
    created = Field(
        alias="created",
        description="When the playground was registered (Unix timestamp)",
        **_unix_ts
    )
    """Created is set on repositories.py.create"""
    updated = Field(
        alias="updated",
        description="When the playground was updated for the last time (Unix timestamp)",
        **_unix_ts
    )
    id = Field(description="The unique identifier of the playground instance", default_factory=uuid.uuid4, alias="_id")
    name = Field(description="The name of the playground instance")
    description = Field(description="The description of the playground instance")
    volume_id = Field(description="The unique identifier of the volume associated with the playground instance")
    container_id = Field(description="The unique identifier of the container associated with the playground instance")
    instance_url = Field(description="The URL of the playground instance")
    client_app_url = Field(description="The URL of the client app associated with the playground instance")
    status = Field(description="The status of the playground instance")


class Playground(BaseModel):
    id: str = PlaygroundFields.id
    name: Optional[str] = PlaygroundFields.name
    description: Optional[str] = PlaygroundFields.description
    volume_id: str = PlaygroundFields.volume_id
    container_id: str = PlaygroundFields.container_id
    instance_url: str = PlaygroundFields.instance_url
    client_app_url: str = PlaygroundFields.client_app_url
    status: str = PlaygroundFields.status
