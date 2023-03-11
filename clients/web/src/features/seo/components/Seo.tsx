import React from "react";
import Head from "next/head";
import { SEO } from "../types";

interface Props {
  seo: SEO;
}

export const Seo: React.FC<Props> = React.memo(({ seo }) => {
  const { title, description, image } = seo;
  return (
    <React.Fragment>
      <Head>
        <title>{title}</title>
        <meta property="og:title" content={title} />
        <meta property="og:type" content="article" />
        <meta property="og:description" content={description} />
        {image && (
          <React.Fragment>
            <meta property="og:image" content={image.filename} />
            <meta property="og:url" content={image.url} />
            <meta name="twitter:card" content="summary_large_image" />
          </React.Fragment>
        )}
      </Head>
    </React.Fragment>
  );
});
