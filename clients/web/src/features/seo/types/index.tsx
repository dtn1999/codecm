export interface SEO {
  title: string;
  description: string;
  image: {
    filename?: string;
    alt?: string;
    url: string;
  };
  socials: SocialProfile[];
}

export interface SocialProfile {
  name: string;
  url: string;
}
