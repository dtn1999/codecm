import React from "react";
import cn from "classnames";
import eruda from "eruda";

//TODO: Take a look at this: https://github.com/liriliri/eruda/blob/master/doc/API.md for better understanding of eruda

const Devtools: React.FC = React.memo(({}) => {
  const iframeRef = React.useRef<HTMLIFrameElement>(null);

  React.useEffect(() => {
    if (!iframeRef.current || !document) return;
    // iframe.onload = () => {
    //   const container = document.getElementById("devtools");
    //   console.log(iframe);
    //   const website = iframe.contentWindow?.document;
    //   if (!website) return;
    //   website.open();
    //   website.write("<div id='devtools'/>");

    //   eruda.position({ x: 20, y: 20 });
    // };
  }, [iframeRef.current]);
  return (
    <iframe
      ref={iframeRef}
      src="https://wafexpress.com"
      onLoad={(e) => {
       const container = document.getElementById("destination");
       if(!container) return;
          eruda.init({
            container,
            tool: [
              "console",
              "elements",
              "network",
              "resources",
              "sources",
              "info",
            ],
            useShadowDom: true,
            autoScale: true,
            defaults: {
              displaySize: 50,
              transparency: 0.9,
              theme: "Monokai Pro",
            },
          });
      }}
      className="h-screen w-full"
    />
  );
});
export default Devtools;
