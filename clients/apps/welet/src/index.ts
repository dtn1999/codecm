import * as Hapi from '@hapi/hapi';
import { Octokit } from 'octokit';
import { unzip, Unzip } from 'node:zlib';
import { pipeline, Readable } from 'node:stream';
import * as fs from 'node:fs';

const octokit = new Octokit({});
const output = fs.createWriteStream('pomodoro');

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: 'localhost',
  });

  server.route({
    method: 'GET',
    path: '/',
    handler: async (request, h) => {
      console.log('Start cloning repository');
      const response = await octokit.request(
        'GET /repos/{owner}/{repo}/zipball/{ref}',
        {
          owner: 'dtn1999',
          repo: 'pomodoro',
          ref: 'master',
        },
      );
      console.log('response', response.data);
      const input = Readable.from(response.data as any);

      pipeline(input, unzip as any, output, (err: any) => {
        if (err) {
          console.error('Pipeline failed.', err);
        } else {
          console.log('Pipeline succeeded.');
        }
      });
      return 'Hello World!';
    },
  });

  await server.start();
  console.log('Server running on %s', server.info.uri);
};

process.on('unhandledRejection', (err) => {
  console.log(err);
  process.exit(1);
});

init();
