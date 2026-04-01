import { Routes, Route, Navigate } from "react-router-dom";
import logo from "../../assets/img/logo.png";
import routes from "routes.js";
import FixedPlugin from "components/fixedPlugin/FixedPlugin";

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
            {/* Sisi Kiri: Area Login Form */}
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

            {/* Sisi Kanan: Style OpenLife & Logo */}
            <div className="absolute right-0 hidden h-full min-h-screen md:block lg:w-[49vw] 2xl:w-[44vw]">
              <div
                className="absolute flex h-full w-full items-center justify-center bg-[#344e86]"
              >
                <div className="flex flex-col items-center">
                  <img 
                    src={logo} 
                    alt="OpenLife Logo" 
                    className="w-96 h-96 object-contain mb-4 filter brightness-0 invert" 
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