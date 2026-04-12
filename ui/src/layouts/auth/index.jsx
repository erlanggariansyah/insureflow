import { Routes, Route, Navigate } from "react-router-dom";
import logo from "../../assets/img/logo.png";
import routes from "routes.js";

export default function Auth() {
  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/auth") {
        return (
          <Route path={`/${prop.path}`} element={prop.component} key={key} />
        );
      } else {
        return null;
      }
    });
  };
  
  document.documentElement.dir = "ltr";
  
  return (
    <div>
      <div className="relative float-right h-full min-h-screen w-full bg-white dark:bg-navy-900">
        <main className={`mx-auto min-h-screen`}>
          <div className="relative flex">
            <div className="mx-auto flex min-h-full w-full flex-col justify-start md:max-w-[75%] lg:max-w-[1013px] lg:px-8 xl:min-h-[100vh] xl:max-w-[1383px] xl:px-0">
              <div className="flex flex-col h-screen justify-center pl-5 pr-5 md:pr-0 md:pl-0 lg:max-w-[48%] xl:max-w-full">
                <Routes>
                  {getRoutes(routes)}
                  <Route
                    path="/"
                    element={<Navigate to="/auth/sign-in" replace />}
                  />
                </Routes>
              </div>
            </div>
            <div className="absolute right-0 hidden h-full min-h-screen md:block lg:w-[49vw] 2xl:w-[44vw]">
              <div className="absolute flex h-full w-full flex-col items-center justify-center bg-gradient-to-br from-blue-700 via-blue-600 to-blue-400 overflow-hidden">
                
                {/* Decorative Background Modern Elements */}
                <div className="absolute -top-[15%] -left-[10%] h-[600px] w-[600px] rounded-full bg-white opacity-10 mix-blend-overlay blur-3xl"></div>
                <div className="absolute -bottom-[15%] -right-[10%] h-[700px] w-[700px] rounded-full bg-blue-200 opacity-20 mix-blend-overlay blur-3xl"></div>
                
                {/* Content */}
                <div className="flex flex-col items-center z-10 px-10">
                  <img 
                    src={logo} 
                    alt="OpenLife Logo" 
                    className="w-80 h-80 object-contain filter brightness-0 invert drop-shadow-[0_20px_20px_rgba(0,0,0,0.2)] transition-transform duration-700 hover:scale-105" 
                  />
                </div>

              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}